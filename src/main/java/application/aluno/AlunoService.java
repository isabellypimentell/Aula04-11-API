package application.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepo;

    public Iterable<AlunoDTO> getAll() {
        return alunoRepo.findAll().stream().map(AlunoDTO::new).toList();
    }

    public AlunoDTO insert(AlunoInsertDTO dados) {
        // Verificando se o aluno já existe com base no email (ou outro campo único)
        Optional<Aluno> alunoExistente = alunoRepo.findByEmail(dados.email());
        if (alunoExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno já cadastrado");
        }

        // Criando e salvando o novo aluno
        Aluno novoAluno = new Aluno(dados);
        return new AlunoDTO(alunoRepo.save(novoAluno));
    }

    public AlunoDTO getOne(long id) {
        Optional<Aluno> resultado = alunoRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }

        return new AlunoDTO(resultado.get());
    }

    public AlunoDTO update(long id, AlunoInsertDTO dadosAluno) {
        Optional<Aluno> resultado = alunoRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }

        Aluno alunoAtualizado = resultado.get();
        alunoAtualizado.setNome(dadosAluno.nome());
        alunoAtualizado.setEmail(dadosAluno.email());
        alunoAtualizado.setTelefone(dadosAluno.telefone());
        alunoAtualizado.setDataMatricula(dadosAluno.dataMatricula());

        return new AlunoDTO(alunoRepo.save(alunoAtualizado));
    }

    public void delete(long id) {
        if (!alunoRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }
        alunoRepo.deleteById(id);
    }
}
