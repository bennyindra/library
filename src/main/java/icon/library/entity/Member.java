package icon.library.entity;

import icon.library.enums.BorrowingMemberStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "member")
@Data
public class Member extends BaseEntity{
    private String name;
    private String nik;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private BorrowingMemberStatus status;
    @Column(name = "total_book_taken")
    private int totalBookTaken;
}
