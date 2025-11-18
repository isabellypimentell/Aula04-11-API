package application.curso;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "cursos")
@Getter @Setter @NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Integer cargaHoraria;
    private String status;
    private LocalDateTime dataCriacao;

    public Curso(CursoInsertDTO dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.cargaHoraria = dados.cargaHoraria();
        this.status = dados.status();
        this.dataCriacao = LocalDateTime.now();
    }
}