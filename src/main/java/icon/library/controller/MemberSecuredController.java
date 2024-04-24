package icon.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import icon.library.dto.MemberRequestDto;
import icon.library.entity.Member;
import icon.library.repository.MemberRepository;
import icon.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/secured/member")
@RequiredArgsConstructor
public class MemberSecuredController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<Member>> getMembers(@RequestParam(defaultValue = "") String memberName, @RequestParam(defaultValue = "") String nik){
        return ResponseEntity.ok(memberRepository.findAllByNameContainingAndNikContaining(memberName, nik, PageRequest.of(0, 10, Sort.by("name").ascending())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable String id){
        return ResponseEntity.ok(memberRepository.findById(id).orElseThrow());
    }


    @PostMapping
    public ResponseEntity<Member> save(@RequestBody MemberRequestDto requestDto) throws JsonProcessingException {
        return ResponseEntity.ok(memberService.save(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> update(@PathVariable String id, @RequestBody MemberRequestDto requestDto){
        return ResponseEntity.ok(memberService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
