package shop.study.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import shop.study.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @WithMockUser(username = "gildong", roles = "USER")
    public void auditingTest() {
        Member member = new Member();
        memberRepository.save(member);

        em.flush();
        em.clear();

        Member member1 = memberRepository.findById(member.getId()).orElseThrow(EntityNotFoundException::new);

        System.out.println("member1.getRegTime() = " + member1.getRegTime());
        System.out.println("member1.getUpdateTime() = " + member1.getUpdateTime());
        System.out.println("member1.getCreatedBy() = " + member1.getCreatedBy());
        System.out.println("member1.getModifiedBy() = " + member1.getModifiedBy());

    }
}