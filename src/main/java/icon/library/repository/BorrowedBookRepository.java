package icon.library.repository;

import icon.library.entity.BorrowedBook;
import icon.library.enums.BorrowingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, String> {

    List<BorrowedBook> findAllByMemberIdAndStatus(String memberId, BorrowingStatus status);
}
