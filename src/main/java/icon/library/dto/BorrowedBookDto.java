package icon.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBookDto {
    private String id;
    private String bookId;
    private int total;
    private LocalDate from;
    private LocalDate to;

}
