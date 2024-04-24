package icon.library.repository;

import icon.library.entity.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends JpaRepository<Journal, String> {

    Page<Journal> findAllByTitleContainingAndAuthorContaining(String title, String author, Pageable pageable);
}
