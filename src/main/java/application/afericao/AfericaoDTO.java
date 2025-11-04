package application.afericao;

public record AfericaoDTO(long id, String idSensor, String unidade, String valor) {
    public AfericaoDTO(Afericao dados) {
        this(dados.getId(), dados.getIdSensor(), dados.getUnidade(), dados.getValor());
    }    
}
