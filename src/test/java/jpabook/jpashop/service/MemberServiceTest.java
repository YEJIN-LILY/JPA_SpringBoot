package jpabook.jpashop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	@Autowired EntityManager em;
	
	@Test
	@Rollback(false)
	public void 회원가입() throws Exception{
		Member member=new Member();
		member.setName("kim");
		
		Long saveId=memberService.join(member);
		
		em.flush();
		assertEquals(member, memberRepository.findOne(saveId));
	}
	
	@Test(expected=IllegalStateException.class)
	public void 중복_회원_예외() throws Exception{
		Member member1=new Member();
		member1.setName("kim1");
		
		Member member2=new Member();
		member1.setName("kim");
		
		memberService.join(member1);
		memberService.join(member2); //예외가 발생해야 
		
		fail("예외가 발생해야 한다.");
	}
}
