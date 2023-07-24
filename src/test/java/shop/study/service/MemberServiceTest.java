package shop.study.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import shop.study.dto.MemberFormDto;
import shop.study.entity.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword("1234");
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    public void saveMemberTest() {
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);

//        assertThat(member.getEmail()).isEqualTo(savedMember.getEmail());
        assertThat(member.getPassword()).isEqualTo(savedMember.getPassword());
    }

    @Test
    public void saveDuplicateMemberTest() {
        Member member1 = createMember();
        Member member2 = createMember();

        memberService.saveMember(member1);
        System.out.println("member1.getEmail() = " + member1.getEmail());
        System.out.println();
        System.out.println("member1.getEmail() = " + member1.getClass());
        System.out.println("member2.getEmail() = " + member2.getClass());



        IllegalStateException returnStatusMessage = assertThrows(IllegalStateException.class, () -> memberService.saveMember(member2));

        Assertions.assertThat(returnStatusMessage.getMessage()).isEqualTo("이미 가입된 회원입니다.");

    }

}