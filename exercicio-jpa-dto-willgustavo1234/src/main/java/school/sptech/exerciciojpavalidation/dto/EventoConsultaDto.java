package school.sptech.exerciciojpavalidation.dto;

import java.time.LocalDate;

public class EventoConsultaDto {

    private Long id;
    private String nome;
    private String local;
    private LocalDate dataEvento;
    private Boolean gratuito;
    private Boolean cancelado;
    private LocalDate dataPublicacao;
    private String status;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public Boolean getCancelado() {
        return cancelado;
    }
    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }
    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }
    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    public String getStatus() {
        if(this.getCancelado()){
            return "CANCELADO";
        } else if(this.getDataEvento().isBefore(LocalDate.now())){
            return "FINALIZADO";
        }
        return "ABERTO";
    }


    
    



}
