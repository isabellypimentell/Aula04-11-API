package application.aluno;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate; // Importação do LocalDate

@Entity
@Table(name = "alunos")
@Getter
@Setter
@NoArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String telefone;
    
    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    // Construtor para inicializar um Aluno
    public Aluno(String nome, String email, String telefone, LocalDate dataMatricula) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataMatricula = dataMatricula;
    }

    // Construtor que usa um DTO
    public Aluno(AlunoDTO dados) {
        this.id = dados.id();
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.dataMatricula = dados.dataMatricula();
    }

    // Construtor que usa outro DTO (provavelmente para inserção)
    public Aluno(AlunoInsertDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.dataMatricula = dados.dataMatricula();
    }
}
