package icon.library.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icon.library.dto.MemberRequestDto;
import icon.library.entity.Member;
import icon.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(MemberRequestDto requestDto) throws JsonProcessingException {
        return memberRepository.save(convertRequest(requestDto));
    }

    private Member convertRequest(MemberRequestDto requestDto) throws JsonProcessingException {
        String string = new ObjectMapper().writeValueAsString(requestDto);
        return new ObjectMapper().readValue(string, Member.class);
    }

    public Member update(String id, MemberRequestDto requestDto){
        Member member = memberRepository.findById(id).orElseThrow();
        member.setName(requestDto.getName());
        member.setNik(requestDto.getNik());
        member.setPhoneNumber(requestDto.getPhoneNumber());
        member.setStatus(requestDto.getStatus());
        member.setTotalBookTaken(requestDto.getTotalBookTaken());
        member.setUpdatedDate(LocalDateTime.now());
        return memberRepository.save(member);
    }

    public void delete(String id){
        Member journal = memberRepository.findById(id).orElseThrow();
        memberRepository.delete(journal);
    }

}
