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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/afericoes")
@Tag(name = "Aferições", description = "Gerencia os registros de aferições de sensores ")
public class AfericaoController {
    @Autowired
    private AfericaoService afericaoService;

    @PostMapping
    @Operation(summary = "Inserir uma nova aferição", description = "Criar um novo registro na base de dados de aferição com os dados enviados.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aferição criada com sucesso"), 
        @ApiResponse(responseCode = "400", description = "Dados Invalidos")
    })

    public AfericaoDTO insert(@RequestBody AfericaoInsertDTO novaAfericao) {
        return afericaoService.insert(novaAfericao);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obem dados de uma aferição específica",description = "Retorna os dados de aferição identificadas pelo ID informado.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aferição encontrada"), 
        @ApiResponse(responseCode = "400", description = "Aferição não encontrada")
    })
    public AfericaoDTO getOne(
        @Parameter(description = "Id da aferição a ser consultada")
        @PathVariable long id) {
        return afericaoService.getOne(id);
    }

    @GetMapping
     @Operation(summary = "Obem dados de uma aferição específica",description = "Retorna os dados de aferição identificadas pelo ID informado.")
    @ApiResponse(responseCode = "200", description = "Aferição encontrada")
    public Iterable<AfericaoDTO> getAll() {
        return afericaoService.getAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza aferiçõ existente", description = "Altera os dados de uma aferição ja cadastrada, identificada pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aferição atualizada com sucesso"), 
        @ApiResponse(responseCode = "400", description = "Aferição não encontrada")
    })
    public AfericaoDTO update(
        @Parameter(description = "ID da aferição a ser atualizada")
        @PathVariable long id, @RequestBody AfericaoInsertDTO novosDados) {
        return afericaoService.update(id, novosDados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma aferição", description = "Exclui permanentemente uma aferição com base no ID informado.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aferição removida com sucesso "), 
        @ApiResponse(responseCode = "400", description = "Aferição não encontrada")
    })
    public void remove(@PathVariable long id) {
        afericaoService.delete(id);
    }
}