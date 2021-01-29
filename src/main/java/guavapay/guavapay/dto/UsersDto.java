package guavapay.guavapay.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    private Long id;

    private String username;

    private String password;
}
