package application.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
class CursoService {
    @Autowired private CursoRepository repo;

    public Iterable<CursoDTO> getAll() {
        return repo.findAll().stream().map(CursoDTO::new).toList();
    }
    public CursoDTO insert(CursoInsertDTO dados) {
        return new CursoDTO(repo.save(new Curso(dados)));
    }

    public CursoDTO getOne(long id) {
        return repo.findById(id).map(CursoDTO::new)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
    }

    public CursoDTO update(long id, CursoInsertDTO dados) {
        var curso = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
        curso.setNome(dados.nome());
        curso.setDescricao(dados.descricao());
        return new CursoDTO(repo.save(curso));
    }

    public void delete(long id) {
        if(!repo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado");
        repo.deleteById(id);
    }
}