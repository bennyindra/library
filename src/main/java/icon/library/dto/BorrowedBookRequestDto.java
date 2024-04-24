package icon.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBookRequestDto {
    List<BorrowedBookDto> borrowedBookDtoList;
    private String memberId;
}
