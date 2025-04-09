package mate.academy.hwspringbootintro.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import mate.academy.hwspringbootintro.model.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book save(Book book) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = entityManager.unwrap(Session.class);
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't add book " + book, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        try (Session session = entityManager.unwrap(Session.class)) {
            CriteriaQuery<Book> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Book.class);
            criteriaQuery.from(Book.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all books!!!", e);
        }
    }
}
