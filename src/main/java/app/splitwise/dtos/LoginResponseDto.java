package app.splitwise.dtos;

import lombok.Data;

@Data
public class LoginResponseDto {
    private Long id;
    private String name;

    private String email;

    private String phoneNumber;


}
