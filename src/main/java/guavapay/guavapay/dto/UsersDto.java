package guavapay.guavapay.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    @JsonIgnore
    private Long id;

    private String username;

    private String password;
}
