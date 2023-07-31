package service;

import dao.UsuarioDAO;
import model.Usuario;
import org.apache.commons.codec.digest.Sha2Crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginService {

    public boolean autenticar(String login, String senha) {

        Usuario c = new UsuarioDAO().getUsuario(login);

        if (c.getLogin() == null) {
            System.out.println("usuário null -> " + c);
            return false;
        } else {


            return c.getLogin().equals(login) && verificarSenha(senha, c.getSenha());
        }

    }

    public static String criptografarSenha(String senha) {
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

    public static boolean verificarSenha(String senhaDigitada, String senhaArmazenada) {
        String senhaCriptografada = criptografarSenha(senhaDigitada);
        return senhaCriptografada.equals(senhaArmazenada);
    }


}
