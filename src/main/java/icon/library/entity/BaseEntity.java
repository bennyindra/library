package icon.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Column(name = "ID", length = 50)
    private String id;

    @Version
    @Column(name = "VERSION", precision = 10)
    private int version;

    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "CREATED_BY", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;

    @Column(name = "UPDATED_BY", length = 50)
    private String updatedBy;

    @Transient
    private String updatedByOther;

    @PrePersist
    void onPrePersist() {
        if (StringUtils.isEmpty(this.getId())){
            this.setId(UUID.randomUUID().toString());
        }
        if (this.getCreatedDate() == null){
            this.setCreatedDate(LocalDateTime.now());
        }
        if (StringUtils.isEmpty(this.getCreatedBy())){
            UserDetails userDetails = getUserDetail();
            if (userDetails != null) {
                this.setCreatedBy(userDetails.getUsername());
            }

        }
    }

    @PreUpdate
    protected void onPreUpdate() {
        UserDetails userDetails = getUserDetail();
        if (userDetails != null) {
            this.setUpdatedBy(userDetails.getUsername());
        }
    }

    private static UserDetails getUserDetail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getPrincipal() instanceof UserDetails ? (UserDetails) authentication.getPrincipal() : null;
    }

    @Override
    public String toString() {
        return "ABaseEntity [id=" + id + ", version=" + version + ", createdDate=" + createdDate + ", createdBy="
                + createdBy + ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy + ", updatedByOther="
                + updatedByOther + "]";
    }
}

