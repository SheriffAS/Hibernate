package hibernate;


import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo {

    public static void main(String[] args) {
        // Session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            // student object
            System.out.println("creating student");
            Student swapStudent = new Student("Lukas", "Pastorek", "Luky@puky.com");

            //start a transaction
            session.beginTransaction();

            //saving student
            System.out.println("saving student");
            session.save(swapStudent);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("generated user "+ swapStudent.getId());
            System.out.println("DONE!");

            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("generated user second "+ swapStudent.getId());

            Student student = session.get(Student.class,swapStudent.getId());
            System.out.println(student.getFirstName());
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}