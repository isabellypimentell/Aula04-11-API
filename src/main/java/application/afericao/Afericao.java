package application.afericao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "afericoes")
@Getter
@Setter
@NoArgsConstructor
public class Afericao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_sensor", nullable = false)
    private String idSensor;
    @Column(nullable = false)
    private String unidade;
    @Column(nullable = false)
    private String valor;

    public Afericao(String idSensor, String unidade, String valor) {
        this.idSensor = idSensor;
        this.unidade = unidade;
        this.valor = valor;
    }

    public Afericao(AfericaoDTO dados) {
        this.id = dados.id();
        this.idSensor = dados.idSensor();
        this.unidade = dados.unidade();
        this.valor = dados.valor();
    }

    public Afericao(AfericaoInsertDTO dados) {
        this.idSensor = dados.idSensor();
        this.unidade = dados.unidade();
        this.valor = dados.valor();
    }
}
