package application.afericao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service    
public class AfericaoService {
     @Autowired
    private AfericaoRepository afericaoRepo;

    public Iterable<AfericaoDTO> getAll() {
        return afericaoRepo.findAll().stream().map(AfericaoDTO::new).toList();
    }

    public AfericaoDTO insert(AfericaoInsertDTO dados) {
        return new AfericaoDTO(afericaoRepo.save(new Afericao(dados)));
    }

    public AfericaoDTO getOne(long id) {
        Optional<Afericao> resultado = afericaoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Afericao não encontrada"
            );
        }

        return new AfericaoDTO(resultado.get());
    }

    public AfericaoDTO update(long id, AfericaoInsertDTO dadosAfericao) {
        Optional<Afericao> resultado = afericaoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Afericao não encontrada"
            );
        }

        resultado.get().setIdSensor(dadosAfericao.idSensor());
        resultado.get().setUnidade(dadosAfericao.unidade());
        resultado.get().setValor(dadosAfericao.valor());

        return new AfericaoDTO(afericaoRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if(!afericaoRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Afericao não encontrada"
            );
        }
        afericaoRepo.deleteById(id);
    }
}
