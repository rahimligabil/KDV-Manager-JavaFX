package com.gabil.kdvapp.dto;

import lombok.*;
import com.gabil.kdvapp.util.ERole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private ERole role;
}
