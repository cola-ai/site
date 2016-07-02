
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.PassoDeRota;
import br.com.cwi.colaai.entity.Rota;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Érico de Souza Loewe
 */
public class RotaViewModel {
    
    private String duracao;
    private String distancia;
    private String polilyne;
    private List<PassoDeRotaViewModel> passos;

    public RotaViewModel() {
    }

    public RotaViewModel(String duracao, String distancia, String polilyne, List<PassoDeRotaViewModel> passos) {
        this.duracao = duracao;
        this.distancia = distancia;
        this.polilyne = polilyne;
        this.passos = passos;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getPolilyne() {
        return polilyne;
    }

    public void setPolilyne(String polilyne) {
        this.polilyne = polilyne;
    }

    public List<PassoDeRotaViewModel> getPassos() {
        return passos;
    }

    public void setPassos(List<PassoDeRotaViewModel> passos) {
        this.passos = passos;
    }

    public Rota toRota() {
        ArrayList<PassoDeRota> passosDeRota = new ArrayList<>();
        passos.forEach((p) -> {passosDeRota.add(p.toPassoDeRota());});
        return new Rota(duracao, distancia, polilyne, passosDeRota);
    }
}
