package school.sptech.exerciciojpavalidation.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class EventoAtualizacaoDto {
    


@NotBlank
@Size(min = 5, max = 100)
private String nome;

@NotBlank
@Size(min = 5, max = 200)
private String local;


@FutureOrPresent
private LocalDate dataEvento;


private Boolean gratuito;

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getLocal() {
    return local;
}

public void setLocal(String local) {
    this.local = local;
}

public LocalDate getDataEvento() {
    return dataEvento;
}

public void setDataEvento(LocalDate dataEvento) {
    this.dataEvento = dataEvento;
}

public Boolean getGratuito() {
    return gratuito;
}

public void setGratuito(Boolean gratuito) {
    this.gratuito = gratuito;
}






}
