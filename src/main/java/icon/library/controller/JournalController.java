package icon.library.controller;

import icon.library.entity.Journal;
import icon.library.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalRepository journalRepository;

    @GetMapping
    public ResponseEntity<Page<Journal>> findAll(@RequestParam(defaultValue = "") String title, @RequestParam(defaultValue = "") String author, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
        Pageable paging = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "name"));
        return ResponseEntity.ok(journalRepository.findAllByTitleContainingAndAuthorContaining(title, author, paging));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journal> findById(@PathVariable String id){
        return ResponseEntity.ok(journalRepository.findById(id).orElseThrow());
    }
}
