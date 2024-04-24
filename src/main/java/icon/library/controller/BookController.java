package icon.library.controller;

import icon.library.entity.Book;
import icon.library.repository.BookRepository;
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
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<Page<Book>> findAll(@RequestParam(defaultValue = "") String title, @RequestParam(defaultValue = "") String author, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
        Pageable paging = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "title"));
        return ResponseEntity.ok(bookRepository.findAllByTitleContainingAndAuthorContaining(title, author, paging));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable String id){
        return ResponseEntity.ok(bookRepository.findById(id).orElseThrow());
    }
}
