package hibernate;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
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

            // delete selected student from class
            // Student student = session.get(Student.class,studentId);
            // session.delete(student);

            // delete student through query
            session.createQuery("delete from Student where id='2'").executeUpdate();

            // commit the update operation
            session.getTransaction().commit();


        } finally {
            factory.close();
        }
    }
}