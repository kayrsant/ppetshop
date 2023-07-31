package teste;

import dao.PetDAO;
import model.Pet;

import java.util.List;

public class TestePetDAO {

    public static void main(String[] args) {
        // Criar instância do PetDAO
        PetDAO petDAO = new PetDAO();
/*
        // Testar o método salvar
        Pet novoPet = new Pet(1, 1, 1, 1, "Rex", 3); // Exemplo de valores para os atributos
        boolean salvou = petDAO.salvar(novoPet);
        System.out.println("Pet salvo: " + salvou);*/

        // Testar o método getPets
        List<Pet> pets = petDAO.getPets(1); // Exemplo de ID do cliente
        System.out.println("Lista de pets:");
        for (Pet pet : pets) {
            System.out.println("ID: " + pet.getId() + " Raça: " + pet.getIdRaca() + " Tipo: "+ pet.getIdTipo() + " Cliente: " + pet.getIdUsuario() + " Nome: " + pet.getNome() + " Idade: " + pet.getIdade());
        }
        /*
        // Testar o método atualizar
        Pet petExistente = new Pet(1, 1, 1, 1, "Luna", 4); // Exemplo de valores para os atributos
        boolean atualizado = petDAO.atualizar(petExistente);
        System.out.println("Pet atualizado: " + atualizado);*/

        /*
        // Testar o método deletar
        Pet petParaDeletar = new Pet(2); // Supondo que o pet com ID 2 exista
        boolean deletado = petDAO.deletar(petParaDeletar);
        System.out.println("Pet deletado: " + deletado);*/
    }
}
