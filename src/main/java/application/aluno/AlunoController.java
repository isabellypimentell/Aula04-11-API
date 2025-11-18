package application.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public AlunoDTO insert(@RequestBody AlunoInsertDTO novoAluno) {
        return alunoService.insert(novoAluno);
    }

    @GetMapping("/{id}")
    public AlunoDTO getOne(@PathVariable long id) {
        return alunoService.getOne(id);
    }

    @GetMapping
    public Iterable<AlunoDTO> getAll() {
        return alunoService.getAll();
    }

    @PutMapping("/{id}")
    public AlunoDTO update(@PathVariable long id, @RequestBody AlunoInsertDTO novosDados) {
        return alunoService.update(id, novosDados);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id) {
        alunoService.delete(id);
    }
}
