import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class EmployeeDAO {
    private static SessionFactory sessionFactory;

    public EmployeeDAO() {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(Employees employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employees employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteEmployee(Long employeeId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            Employees employee = session.get(Employees.class, employeeId);
            transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Employees> getAllEmployees() {
        try (Session session = sessionFactory.openSession()) {
            Query<Employees> query = session.createQuery("FROM Employees", Employees.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Employees> getEmployeesByCriteria(String criteria, String value) {
        try (Session session = sessionFactory.openSession()) {
            String queryStr = "FROM Employees WHERE " + criteria + " = :value";
            Query<Employees> query = session.createQuery(queryStr, Employees.class);
            query.setParameter("value", value);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Employees getEmployeeById(Long employeeId) {
        try (Session session = sessionFactory.openSession()) {
            Employees employee = session.get(Employees.class, employeeId);
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}