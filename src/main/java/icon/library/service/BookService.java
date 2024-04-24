package icon.library.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icon.library.dto.BookRequestDto;
import icon.library.dto.JournalRequestDto;
import icon.library.entity.Book;
import icon.library.entity.Journal;
import icon.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book save(BookRequestDto requestDto) throws JsonProcessingException {
        return bookRepository.save(convertRequest(requestDto));
    }
    private Book convertRequest(BookRequestDto requestDto) throws JsonProcessingException {
        String string = new ObjectMapper().writeValueAsString(requestDto);
        return new ObjectMapper().readValue(string, Book.class);
    }

    public Book update(String id, BookRequestDto requestDto){
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setPublishedYear(requestDto.getPublishedYear());
        book.setRoomLocation(requestDto.getRoomLocation());
        book.setCabinetLocation(requestDto.getCabinetLocation());
        book.setShelfLocation(requestDto.getShelfLocation());

        book.setTotalBook(requestDto.getTotalBook());
        book.setCriteria(requestDto.getCriteria());
        book.setGenre(requestDto.getGenre());

        return bookRepository.save(book);
    }

    public void delete(String id){
        Book journal = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(journal);
    }
}
