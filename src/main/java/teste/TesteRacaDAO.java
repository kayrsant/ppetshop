package teste;

import dao.PetDAO;
import dao.RacaDAO;
import model.Pet;
import model.Raca;

import java.util.ArrayList;
import java.util.List;

public class TesteRacaDAO {
    public static void main(String[] args) {

        RacaDAO r = new RacaDAO();
/*
        boolean salvou = r.inserirRaca(1, "Akita");
        System.out.println("Pet salvo: " + salvou);*/

        ArrayList<Raca> racas = r.getRacas();
        System.out.println("Lista de Raças:");
        for (Raca raca : racas){
            System.out.println("ID: " + raca.getId() + " NOME: " + raca.getNome() + " ID TIPO: " + raca.getIdTipo());
        }

        /*
        boolean atualizado = r.atualizarRaca(1, 1, "Beagle");
        System.out.println("Pet atualizado: " + atualizado);

        System.out.println("Lista de Raças:");
        for (Raca raca : racas){
            System.out.println("ID: " + raca.getId() + " NOME:" + raca.getNome() + " ID TIPO:" + raca.getIdTipo());
        }

        boolean deletado = r.deletarRaca(2);
        System.out.println("Pet deletado: " + deletado);*/
    }

}
