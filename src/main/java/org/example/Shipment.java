package org.example;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.ContentType;

//@Getter
@Getter
@Setter
@AllArgsConstructor
public class Shipment {
    private final Integer id;
    private final ContentType type;
}
