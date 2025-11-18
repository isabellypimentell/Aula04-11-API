package application.matricula;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepo;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoService cursoService;

    public Iterable<MatriculaDTO> getAll() {
        return matriculaRepo.findAll().stream().map(MatriculaDTO::new).toList();
    }

    public MatriculaDTO insert(MatriculaInsertDTO dados) {
        // Validar se o aluno e o curso existem
        alunoService.getOne(dados.aluno().getId());
        cursoService.getOne(dados.curso().getId());

        Matricula novaMatricula = new Matricula(dados);
        return new MatriculaDTO(matriculaRepo.save(novaMatricula));
    }

    public MatriculaDTO getOne(long id) {
        Optional<Matricula> resultado = matriculaRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Matrícula não encontrada"
            );
        }

        return new MatriculaDTO(resultado.get());
    }

    public MatriculaDTO update(long id, MatriculaInsertDTO dadosMatricula) {
        Optional<Matricula> resultado = matriculaRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Matrícula não encontrada"
            );
        }

        Matricula matriculaAtualizada = resultado.get();
        matriculaAtualizada.setDataMatricula(dadosMatricula.dataMatricula());
        matriculaAtualizada.setStatus(dadosMatricula.status());
        matriculaAtualizada.setAluno(dadosMatricula.aluno());
        matriculaAtualizada.setCurso(dadosMatricula.curso());

        return new MatriculaDTO(matriculaRepo.save(matriculaAtualizada));
    }

    public void delete(long id) {
        if (!matriculaRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Matrícula não encontrada"
            );
        }
        matriculaRepo.deleteById(id);
    }
}
