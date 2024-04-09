package school.sptech.exerciciojpavalidation.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import school.sptech.exerciciojpavalidation.dto.EventoAtualizacaoDto;
import school.sptech.exerciciojpavalidation.dto.EventoConsultaDto;
import school.sptech.exerciciojpavalidation.dto.EventoCriacaoDto;
import school.sptech.exerciciojpavalidation.dto.EventoMapper;
import school.sptech.exerciciojpavalidation.entity.Evento;
import school.sptech.exerciciojpavalidation.repository.EventoRepository;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    final EventoRepository repository;

    public EventoController(EventoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<EventoConsultaDto> create(@RequestBody @Valid EventoCriacaoDto evento) {
        Evento eventoConvertido = EventoMapper.toEntity(evento);
        Evento eventoCriado = this.repository.save(eventoConvertido);
        EventoConsultaDto eventoConsulta = EventoMapper.toDto(eventoCriado);
        return ResponseEntity.status(201).body(eventoConsulta);
    }

    @GetMapping
    public ResponseEntity<List<EventoConsultaDto>> listAll(){
        List<Evento> eventos = repository.findAll();

        if(eventos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        List<EventoConsultaDto> dtos = EventoMapper.toDto(eventos);
        return ResponseEntity.status(200).body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoConsultaDto> getById(@PathVariable Long id) {
        Optional<Evento> evento = this.repository.findById(id);

        if(evento.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        EventoConsultaDto dto = EventoMapper.toDto(evento.get());
        return ResponseEntity.status(200).body(dto);

    }

    @GetMapping("/gratuitos")
    public ResponseEntity<List<EventoConsultaDto>> listAllFree() {
        List<Evento> eventos = repository.findEventosByGratuitoIsTrue();
        
        if(eventos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        List<EventoConsultaDto> dtos = EventoMapper.toDto(eventos);
        return ResponseEntity.status(200).body(dtos);
    }

    @GetMapping("/data")
    public ResponseEntity<List<EventoConsultaDto>> listByDate(@RequestParam LocalDate ocorrencia){
        List<Evento> eventos = repository.findEventosByDataEventoIsOrDataPublicacaoIs(ocorrencia, ocorrencia);
        if(eventos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        List<EventoConsultaDto> dtos = EventoMapper.toDto(eventos);
        return ResponseEntity.status(200).body(dtos);
    }
    @GetMapping("/periodo")
    public ResponseEntity<List<EventoConsultaDto>>
    listByDateRange(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim
    ) {
        if(inicio.isBefore(fim) || inicio.isEqual(fim)) {
            List<Evento> eventos = repository.findEventosByDataEventoIsBetween(inicio, fim);
            if (eventos.isEmpty()) {
                return ResponseEntity.status(204).build();
            }
            List<EventoConsultaDto> dtos = EventoMapper.toDto(eventos);
            return ResponseEntity.status(200).body(dtos);
        }
        return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if(repository.findById(id).isEmpty()){
            return ResponseEntity.status(404).build();
        }
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoConsultaDto> updateByID(@PathVariable Long id, @RequestBody @Valid EventoAtualizacaoDto eventoNovo){
        Evento evento = repository.findEventoByIdIs(id);
        if(evento == null){
            return ResponseEntity.status(404).build();
        }
        if(evento.getDataEvento().isBefore(LocalDate.now()) || evento.getCancelado()) {
            return ResponseEntity.status(422).build();

        }
        Evento eventoConvertido = EventoMapper.toUpdate(evento, eventoNovo);
        repository.save(eventoConvertido);
        EventoConsultaDto dto = EventoMapper.toDto(eventoConvertido);
        return ResponseEntity.status(200).body(dto);
    }

    @PatchMapping("/{id}/cancelamento")
    public ResponseEntity<EventoConsultaDto> cancelById(@PathVariable Long id){
        Evento evento = repository.findEventoByIdIs(id);
        if(evento == null){
            return ResponseEntity.status(404).build();
        }
        if(evento.getDataEvento().isBefore(LocalDate.now()) || evento.getCancelado()) {
            return ResponseEntity.status(422).build();

        }
        evento.setCancelado(true);
        repository.save(evento);
        EventoConsultaDto dto = EventoMapper.toDto(evento);
        return ResponseEntity.status(204).build();
    }

}
