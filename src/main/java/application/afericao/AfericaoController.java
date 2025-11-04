package application.afericao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/afericoes")
public class AfericaoController {
    @Autowired
    private AfericaoService afericaoService;

    @PostMapping
    public AfericaoDTO insert(@RequestBody AfericaoInsertDTO novaAfericao) {
        return afericaoService.insert(novaAfericao);
    }

    @GetMapping("/{id}")
    public AfericaoDTO getOne(@PathVariable long id) {
        return afericaoService.getOne(id);
    }

    @GetMapping
    public Iterable<AfericaoDTO> getAll() {
        return afericaoService.getAll();
    }

    @PutMapping("/{id}")
    public AfericaoDTO update(@PathVariable long id, @RequestBody AfericaoInsertDTO novosDados) {
        return afericaoService.update(id, novosDados);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id) {
        afericaoService.delete(id);
    }
}