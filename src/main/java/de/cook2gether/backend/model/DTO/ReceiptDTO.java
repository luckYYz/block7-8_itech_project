package de.cook2gether.backend.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiptDTO {
    private int receiptId;
    private String difficulty;
    private String duration;
    private String title;
    private String description;
    private String ingredients;
    private long saves;
}
