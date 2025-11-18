package application.matricula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @PostMapping
    public MatriculaDTO insert(@RequestBody MatriculaInsertDTO novaMatricula) {
        return matriculaService.insert(novaMatricula);
    }

    @GetMapping("/{id}")
    public MatriculaDTO getOne(@PathVariable long id) {
        return matriculaService.getOne(id);
    }

    @GetMapping
    public Iterable<MatriculaDTO> getAll() {
        return matriculaService.getAll();
    }

    @PutMapping("/{id}")
    public MatriculaDTO update(@PathVariable long id, @RequestBody MatriculaInsertDTO novosDados) {
        return matriculaService.update(id, novosDados);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id) {
        matriculaService.delete(id);
    }
}
