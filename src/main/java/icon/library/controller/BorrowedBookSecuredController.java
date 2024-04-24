package icon.library.controller;

import icon.library.dto.BorrowedBookRequestDto;
import icon.library.entity.BorrowedBook;
import icon.library.service.BorrowedBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/secured/borrow/book")
@RequiredArgsConstructor
public class BorrowedBookSecuredController {

    private final BorrowedBookService bookService;

    @PostMapping
    public ResponseEntity<List<BorrowedBook>> borrow(@RequestBody BorrowedBookRequestDto borrowedBookRequestDto){
        return ResponseEntity.ok(bookService.booking(borrowedBookRequestDto));
    }

    @PostMapping("/returned")
    public ResponseEntity<List<BorrowedBook>> returning(@RequestBody BorrowedBookRequestDto borrowedBookRequestDto) {
        return ResponseEntity.ok(bookService.returned(borrowedBookRequestDto));
    }
}
