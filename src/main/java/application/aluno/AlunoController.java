package application.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Gerencia os dados dos alunos") // Adicionado
public class AlunoController {
    @Autowired private AlunoService service;

    @GetMapping
    @Operation(summary = "Listar todos os alunos", description = "Retorna uma lista com todos os alunos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    public Iterable<AlunoDTO> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluno por ID", description = "Retorna um único aluno baseado no ID fornecido")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aluno encontrado"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public AlunoDTO getOne(@PathVariable long id) { return service.getOne(id); }

    @PostMapping
    @Operation(summary = "Cadastrar novo aluno", description = "Cria um novo registro de aluno")
    @ApiResponse(responseCode = "200", description = "Aluno criado com sucesso")
    public AlunoDTO insert(@RequestBody AlunoInsertDTO dados) { return service.insert(dados); }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluno", description = "Atualiza os dados de um aluno existente")
    public AlunoDTO update(@PathVariable long id, @RequestBody AlunoInsertDTO dados) {
        return service.update(id, dados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover aluno", description = "Exclui um aluno do sistema")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}