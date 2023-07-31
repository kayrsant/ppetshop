package service;

import dao.PetDAO;
import dao.UsuarioDAO;
import model.Pet;
import model.Usuario;

import java.util.ArrayList;

public class PetService {

    public boolean cadastrar(String idRaca, String idTipo, String idUsuario, String nome, String idade){
        int idRacaInt = Integer.parseInt(idRaca);
        int idTipoInt = Integer.parseInt(idTipo);
        int idUsuarioInt = Integer.parseInt(idUsuario);
        int idadeInt = Integer.parseInt(idade);

        if(new PetDAO().salvar(new Pet(idRacaInt, idTipoInt, idUsuarioInt, nome, idadeInt))){
            System.out.println("Pet criado!");
            return true;
        } else{
            System.out.println("Problema na criação do pet");
            return false;
        }
    }

    public boolean hasPet(int idUsuario) {

        ArrayList<Pet> pets = new PetDAO().getPets(idUsuario);

        if (pets.isEmpty()) {
            System.out.println("pets vazio -> " + pets);
            return false;
        } else {
            return true;
        }

    }
}
