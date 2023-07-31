package teste;

import dao.RacaDAO;
import dao.TipoDAO;
import model.Raca;
import model.Tipo;

import java.util.ArrayList;

public class TesteTipoDAO {

    public static void main(String[] args) {

        TipoDAO t = new TipoDAO();

        /*
        boolean salvou = t.inserirTipo("Gato");
        System.out.println("Tipo salvo: " + salvou);



        ArrayList<Tipo> tipos = t.getTipos();
        System.out.println("Lista de Tipos:");
        for (Tipo tipo : tipos){
            System.out.println("ID: " + tipo.getId() + " NOME: " + tipo.getNome());
        }


        boolean atualizado = t.atualizarTipo(1, "Cachorro");
        System.out.println("Tipo atualizado " + atualizado);

        System.out.println("Lista de Tipos:");
        for (Tipo tipo : tipos){
            System.out.println("ID: " + tipo.getId() + " NOME: " + tipo.getNome());
        }

        /*

        boolean deletado = t.deletarTipo(2);
        System.out.println("Tipo deletado: " + deletado);
         */
    }

}
