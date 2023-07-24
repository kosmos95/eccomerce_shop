package shop.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.study.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
