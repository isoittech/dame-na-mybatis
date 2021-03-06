package sample.java.damebatis.domain.dto;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class LoginUserDto extends CreateUserDto {

  public LoginUserDto(String email) {
    super(email);
  }
}
