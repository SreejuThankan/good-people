package com.goodpeople.app.medicine;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Builder
@Data
@Document(collection = "person")
public class Medicine {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal dosageMg;
}
