package application.curso;

import java.time.LocalDateTime; // <-- Importação necessária

public record CursoDTO(
        long id,
        String nome,
        String descricao,
        Integer cargaHoraria,
        String status,
        LocalDateTime dataCriacao
) {

    public CursoDTO(Curso dados) {
        this(
            dados.getId(),
            dados.getNome(),
            dados.getDescricao(),
            dados.getCargaHoraria(),
            dados.getStatus(),
            dados.getDataCriacao()
        );
    }
}
