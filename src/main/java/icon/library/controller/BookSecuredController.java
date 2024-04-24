package icon.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import icon.library.dto.BookRequestDto;
import icon.library.entity.Book;
import icon.library.service.BookService;
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
@RequestMapping("/api/secured/book")
@RequiredArgsConstructor
public class BookSecuredController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody BookRequestDto bookRequestDto) throws JsonProcessingException {
        return ResponseEntity.ok(bookService.save(bookRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable String id, @RequestBody BookRequestDto bookRequestDto){
        return ResponseEntity.ok(bookService.update(id, bookRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
