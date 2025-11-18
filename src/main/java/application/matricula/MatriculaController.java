package application.matricula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/matriculas")
@Tag(name = "Matrículas", description = "Gerencia o vínculo entre alunos e cursos")
public class MatriculaController {
    @Autowired private MatriculaService service;

    @GetMapping
    @Operation(summary = "Listar todas as matrículas", description = "Retorna o histórico completo de matrículas")
    @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    public Iterable<MatriculaDTO> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar matrícula por ID", description = "Obtém os detalhes de uma matrícula específica")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matrícula encontrada"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public MatriculaDTO getOne(@PathVariable long id) { return service.getOne(id); }

    @PostMapping
    @Operation(summary = "Realizar nova matrícula", description = "Vincula um aluno a um curso")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matrícula realizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno ou Curso não encontrado")
    })
    public MatriculaDTO insert(@RequestBody MatriculaInsertDTO dados) { return service.insert(dados); }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar matrícula", description = "Altera dados da matrícula, como status ou curso")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matrícula atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public MatriculaDTO update(@PathVariable long id, @RequestBody MatriculaInsertDTO dados) {
        return service.update(id, dados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancelar matrícula", description = "Remove o registro de matrícula do sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matrícula removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}