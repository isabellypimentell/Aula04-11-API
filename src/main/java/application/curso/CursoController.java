package application.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "Gerencia os cursos disponíveis na instituição")
public class CursoController {
    @Autowired private CursoService service;

    @GetMapping
    @Operation(summary = "Listar todos os cursos", description = "Retorna a lista completa de cursos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    public Iterable<CursoDTO> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar curso por ID", description = "Retorna os detalhes de um curso específico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso encontrado"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public CursoDTO getOne(@PathVariable long id) { return service.getOne(id); }

    @PostMapping
    @Operation(summary = "Criar novo curso", description = "Cadastra um novo curso com nome, carga horária, etc.")
    @ApiResponse(responseCode = "200", description = "Curso criado com sucesso")
    public CursoDTO insert(@RequestBody CursoInsertDTO dados) { return service.insert(dados); }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar curso", description = "Atualiza as informações de um curso existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public CursoDTO update(@PathVariable long id, @RequestBody CursoInsertDTO dados) {
        return service.update(id, dados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover curso", description = "Exclui um curso do sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}