package application.curso;

import java.time.LocalDateTime;

public record CursoDTO(Long id, String nome, String descricao, Integer cargaHoraria, String status, LocalDateTime dataCriacao) {
    public CursoDTO(Curso c) {
        this(c.getId(), c.getNome(), c.getDescricao(), c.getCargaHoraria(), c.getStatus(), c.getDataCriacao());
    }
}