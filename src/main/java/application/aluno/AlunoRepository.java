package application.aluno;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    public Optional<Aluno> findByEmail(String email);
    public Optional<Aluno> findByTelefone(String telefone);
}
