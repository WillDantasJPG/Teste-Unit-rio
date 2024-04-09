package school.sptech.exerciciojpavalidation.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EventoCriacaoDto {
    


@NotBlank
@Size(min = 5, max = 100)
private String nome;

@NotBlank
@Size(min = 5, max = 150)
private String local;


@FutureOrPresent
@NotNull
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
