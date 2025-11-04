package application.afericao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AfericaoRepository extends JpaRepository<Afericao, Long> {
    public Optional<Afericao> findByIdSensor(String idSensor);
}
