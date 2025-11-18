package application.curso;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime; // <-- IMPORTAÇÃO QUE FALTAVA

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @Column(nullable = false)
    private String status;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    public Curso(String nome, String descricao, Integer cargaHoraria, String status, LocalDateTime dataCriacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public Curso(CursoDTO dados) {
        this.id = dados.id();
        this.nome = dados.nome();
       	this.descricao = dados.descricao();
        this.cargaHoraria = dados.cargaHoraria();
        this.status = dados.status();
        this.dataCriacao = dados.dataCriacao();
    }

    public Curso(CursoInsertDTO dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.cargaHoraria = dados.cargaHoraria();
        this.status = dados.status();
        this.dataCriacao = dados.dataCriacao();
    }
}
