package application.afericao;

public record AfericaoInsertDTO(String idSensor, String unidade, String valor) {
    public AfericaoInsertDTO(Afericao dados) {
        this(dados.getIdSensor(), dados.getUnidade(), dados.getValor());
    }    
}
