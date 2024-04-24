package icon.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import icon.library.entity.Member;

@JsonIgnoreProperties({"updatedDate", "updatedBy", "createdDate", "createdBy", "version"})
public class MemberRequestDto extends Member {
}
