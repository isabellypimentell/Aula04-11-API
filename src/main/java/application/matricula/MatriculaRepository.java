package application.matricula;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    public Optional<Matricula> findByAlunoIdAndCursoId(long alunoId, long cursoId);
}
