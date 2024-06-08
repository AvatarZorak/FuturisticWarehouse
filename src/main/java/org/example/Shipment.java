package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.ContentType;

@Getter
@Setter
@AllArgsConstructor
public class Shipment {
    private Integer id;
    private ContentType type;
}
