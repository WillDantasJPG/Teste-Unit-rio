package school.sptech.exerciciojpavalidation.dto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

import school.sptech.exerciciojpavalidation.entity.Evento;

public class EventoMapper {


    
    public static Evento toEntity(EventoCriacaoDto dto) {
        Evento evento = new Evento();
        evento.setNome(dto.getNome());
        evento.setLocal(dto.getLocal());
        evento.setDataEvento(dto.getDataEvento());
        evento.setGratuito(dto.getGratuito());
        evento.setCancelado(false);
        return evento;
    
    }
    
    public static EventoConsultaDto toDto(Evento evento) {
        if(Objects.isNull(evento)){
            return null;
        }
        EventoConsultaDto dto = new EventoConsultaDto();

        dto.setId(evento.getId());
        dto.setNome(evento.getNome());  
        dto.setLocal(evento.getLocal());
        dto.setDataEvento(evento.getDataEvento());
        dto.setGratuito(evento.getGratuito());
        dto.setCancelado(evento.getCancelado());
        dto.setDataPublicacao(evento.getDataPublicacao());
        return dto;
    }

    public static List<EventoConsultaDto> toDto(List<Evento> eventos) {
        List<EventoConsultaDto> eventosDto = new ArrayList<>();
        for (Evento evento : eventos) {
            eventosDto.add(toDto(evento));
        }
        return eventosDto;
    }

    public static Evento toUpdate(Evento evento, EventoAtualizacaoDto dto) {
        if(dto.getNome() != null){
            evento.setNome(dto.getNome());
        }
        if(dto.getLocal() != null){
            evento.setLocal(dto.getLocal());
        }
        if(dto.getDataEvento() != null){
            evento.setDataEvento(dto.getDataEvento());
        }
        if(dto.getGratuito() != null){
            evento.setGratuito(dto.getGratuito());
        }
        return evento;
    }



    
}
