package icon.library.entity;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class References extends BaseEntity{

    private String title;
    private String author;
    private String publisher;
    @Column(name = "published_year")
    private int publishedYear;
    @Column(name = "room_location")
    private String roomLocation;
    @Column(name = "cabinet_location")
    private String cabinetLocation;
    @Column(name = "shelf_location")
    private String shelfLocation;
}
