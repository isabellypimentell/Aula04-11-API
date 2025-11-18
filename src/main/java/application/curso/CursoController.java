package application.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public CursoDTO insert(@RequestBody CursoInsertDTO novoCurso) {
        return cursoService.insert(novoCurso);
    }

    @GetMapping("/{id}")
    public CursoDTO getOne(@PathVariable long id) {
        return cursoService.getOne(id);
    }

    @GetMapping
    public Iterable<CursoDTO> getAll() {
        return cursoService.getAll();
    }

    @PutMapping("/{id}")
    public CursoDTO update(@PathVariable long id, @RequestBody CursoInsertDTO novosDados) {
        return cursoService.update(id, novosDados);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id) {
        cursoService.delete(id);
    }
}
