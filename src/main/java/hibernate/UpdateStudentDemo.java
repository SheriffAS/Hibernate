package hibernate;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            int studentId = 1;
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on id
            Student student = session.get(Student.class,studentId);

            // updating the student
            student.setFirstName("nigga");

            // commit the update operation
            session.getTransaction().commit();


            session = factory.getCurrentSession();
            session.beginTransaction();

            // update all users
            session.createQuery("update Student set first_name='nigga'").executeUpdate();

            // commit the update operation
            session.getTransaction().commit();


        } finally {
            factory.close();
        }
    }
}