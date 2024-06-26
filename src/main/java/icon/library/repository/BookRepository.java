package icon.library.repository;

import icon.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    Page<Book> findAllByTitleContainingAndAuthorContaining(String title, String author, Pageable pageable);
}
