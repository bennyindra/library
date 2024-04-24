package icon.library.entity;

import icon.library.enums.BorrowingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "borrowed_book")
@Data
public class BorrowedBook extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(name = "from_date")
    private LocalDate from;
    @Column(name = "to_date")
    private LocalDate to;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private BorrowingStatus status;
    private int total;

}
