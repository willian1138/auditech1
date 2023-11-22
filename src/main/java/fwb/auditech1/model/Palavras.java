package fwb.auditech1.model;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Palavras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    List<String> primeiroNivel = List.of("abacaxi", "passaro", "cobra");
    List<String> segundoNivel = List.of("abacaxi", "arvore", "onomatopeia");
    List<String> terceiroNivel = List.of("abacaxi", "obscuro", "gótico");
    List<String> quartoNivel = List.of("abacaxi", "pepita", "teatro");
    
}
