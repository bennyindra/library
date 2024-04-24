package icon.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "book")
@Data
public class Book extends References{

    @Column(name = "total_book")
    private int totalBook;
    private String criteria; // rare/normal
    private String genre;

}
