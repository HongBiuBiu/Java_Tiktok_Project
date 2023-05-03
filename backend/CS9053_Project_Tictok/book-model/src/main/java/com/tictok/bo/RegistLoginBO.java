package com.tictok.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegistLoginBO {

    @NotBlank(message = "Cell phone number cannot be empty")
    @Length(min = 11, max = 11, message = "Incorrect phone length")
    private String mobile;
    @NotBlank(message = "Verification code cannot be empty")
    private String smsCode;

}
