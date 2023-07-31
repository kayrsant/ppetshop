package service;

import dao.UsuarioDAO;
import model.Usuario;
import org.apache.commons.codec.digest.Sha2Crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public class CadastrarService {

    public boolean cadastrar(String nome, String cpf, String email, String data, String login, String senha, String permissao){

        Usuario c;

        if(validarCPF(cpf)){
            System.out.println("CPF: " + cpf + " = VÁLIDO!");
            c = new UsuarioDAO().salvar(nome, cpf, email, data, login, criptografarSenha(senha), permissao);

            if(c == null){
                System.out.println("Problema na criação do usuário: " + c.getNome());
                return false;
            } else{
                System.out.println("Usuário " + nome + " criado!");
                return true;
            }
        } else{
            System.out.println("CPF " + cpf + " = INVÁLIDO!");
            return false;
        }
    }

    public boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        boolean todosDigitosIguais = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosDigitosIguais = false;
                break;
            }
        }
        if (todosDigitosIguais) {
            return false;
        }

        int digitoVerificador1 = calcularDigitoVerificador(cpf.substring(0, 9));
        if (digitoVerificador1 != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        int digitoVerificador2 = calcularDigitoVerificador(cpf.substring(0, 10));
        if (digitoVerificador2 != Character.getNumericValue(cpf.charAt(10))) {
            return false;
        }

        return true;
    }

    private int calcularDigitoVerificador(String cpfParcial) {
        int soma = 0;
        for (int i = cpfParcial.length() - 1, multiplicador = 2; i >= 0; i--, multiplicador++) {
            soma += Character.getNumericValue(cpfParcial.charAt(i)) * multiplicador;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    public String criptografarSenha(String senha) {
        try {

            if (senha.length() > 100) {
                senha = senha.substring(0, 100);
            }


            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(senha.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
