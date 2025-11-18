package application.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepo;

    public Iterable<CursoDTO> getAll() {
        return cursoRepo.findAll().stream().map(CursoDTO::new).toList();
    }

    public CursoDTO insert(CursoInsertDTO dados) {
        // Verificando se o curso já existe com base no nome
        Optional<Curso> cursoExistente = cursoRepo.findByNome(dados.nome());
        if (cursoExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Curso já cadastrado");
        }

        // Criando e salvando o novo curso
        Curso novoCurso = new Curso(dados);
        return new CursoDTO(cursoRepo.save(novoCurso));
    }

    public CursoDTO getOne(long id) {
        Optional<Curso> resultado = cursoRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado");
        }

        return new CursoDTO(resultado.get());
    }

    public CursoDTO update(long id, CursoInsertDTO dadosCurso) {
        Optional<Curso> resultado = cursoRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado");
        }

        Curso cursoAtualizado = resultado.get();
        cursoAtualizado.setNome(dadosCurso.nome());
        cursoAtualizado.setDescricao(dadosCurso.descricao());
        cursoAtualizado.setCargaHoraria(dadosCurso.cargaHoraria());
        cursoAtualizado.setStatus(dadosCurso.status());
        cursoAtualizado.setDataCriacao(dadosCurso.dataCriacao());

        return new CursoDTO(cursoRepo.save(cursoAtualizado));
    }

    public void delete(long id) {
        if (!cursoRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado");
        }
        cursoRepo.deleteById(id);
    }
}
