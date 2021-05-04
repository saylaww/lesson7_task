package uz.pdp.lesson7.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotNull(message = "FullName bo'sh bo'lmasin")
    private String fullName;

    @NotNull(message = "username bo'sh bo'lmasin")
    private String username;

    @NotNull(message = "Password bo'sh bo'lmasin")
    private String password;

    @NotNull(message = "prePassword bo'sh bo'lmasin")
    private String prePassword;


}
