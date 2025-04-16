/*package mate.academy.hwspringbootintro.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import mate.academy.hwspringbootintro.exception.DataProcessingException;
import mate.academy.hwspringbootintro.model.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

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
            throw new DataProcessingException("Can't add book " + book, e) {
            };
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Book> query = session.createQuery("from Book", Book.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all books!!!", e);
        }
    }

    @Override
    public Optional<Book> getById(Long id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Book> query = session.createQuery("from Book where id = :id", Book.class)
                    .setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get book by given ID:" + id, e);
        }
    }
}*/
