package icon.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Table
@Entity(name = "journal")
@Data
public class Journal extends References{

    @Column(name = "journal_type")
    private String journalType;
}
