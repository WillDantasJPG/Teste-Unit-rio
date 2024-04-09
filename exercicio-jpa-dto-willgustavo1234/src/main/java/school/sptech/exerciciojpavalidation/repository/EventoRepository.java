package school.sptech.exerciciojpavalidation.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import school.sptech.exerciciojpavalidation.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long>{


    List<Evento> findEventosByGratuitoIsTrue();
    List<Evento> findEventosByDataEventoIsOrDataPublicacaoIs(LocalDate dataEvento, LocalDate dataPublicacao);
    List<Evento> findEventosByDataEventoIsBetween(LocalDate inicio, LocalDate fim);

    Evento findEventoByCanceladoIsFalseAndDataEventoIsGreaterThanEqualAndIdIs(LocalDate dataEvento, Long id);

    Evento findEventoByIdIs(Long id);
}

