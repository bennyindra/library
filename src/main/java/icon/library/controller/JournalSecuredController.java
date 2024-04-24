package icon.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import icon.library.dto.JournalRequestDto;
import icon.library.entity.Journal;
import icon.library.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/secured/journal")
@RequiredArgsConstructor
public class JournalSecuredController {

    private final JournalService journalService;

    @PostMapping
    public ResponseEntity<Journal> save(@RequestBody JournalRequestDto bookRequestDto) throws JsonProcessingException {
        return ResponseEntity.ok(journalService.save(bookRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Journal> update(@PathVariable String id, @RequestBody JournalRequestDto bookRequestDto){
        return ResponseEntity.ok(journalService.update(id, bookRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        journalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
