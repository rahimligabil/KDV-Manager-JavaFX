package com.gabil.kdvapp.dto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotebookDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    // private String category; // Örn: "Kişisel", "İş", "Okul"
    private boolean pinned;  // Sabitlenmiş not mu?
    private UserDTO userDTO; //Composition

    // Constructors
    // Getter and Setters
}