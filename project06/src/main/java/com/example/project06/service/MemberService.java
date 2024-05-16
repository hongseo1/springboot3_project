package com.example.project06.service;


import com.example.project06.entity.Member;
import com.example.project06.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //에러(중복 회원가입등)발생 시 롤백
@RequiredArgsConstructor //의존성 주입(Bean 객체 생성자)
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member); //중복이 있는지
        return memberRepository.save(member); //중복 없으면 저장
    }
    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder() //UserDetail을 구현하고 있는 User 객체를 반환
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
