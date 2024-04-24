package icon.library.repository;

import icon.library.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    List<Member> findAllByNameContainingAndNikContaining(String name, String nik, Pageable pageable);
}
