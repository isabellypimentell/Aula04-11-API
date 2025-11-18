package application.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
class AlunoService {
    @Autowired private AlunoRepository repo;

    public Iterable<AlunoDTO> getAll() {
        return repo.findAll().stream().map(AlunoDTO::new).toList();
    }
    public AlunoDTO insert(AlunoInsertDTO dados) {
        return new AlunoDTO(repo.save(new Aluno(dados)));
    }
    public AlunoDTO getOne(long id) {
        return repo.findById(id).map(AlunoDTO::new)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }
    public AlunoDTO update(long id, AlunoInsertDTO dados) {
        var aluno = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        aluno.setNome(dados.nome());
        aluno.setEmail(dados.email());
        return new AlunoDTO(repo.save(aluno));
    }
    public void delete(long id) {
        if(!repo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        repo.deleteById(id);
    }
}