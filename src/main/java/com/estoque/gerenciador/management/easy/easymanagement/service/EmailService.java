package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmail(Email email){
        var mensagem = new SimpleMailMessage();
        mensagem.setFrom("usuarios@easymanagement.com");
        mensagem.setTo(email.getTo());
        mensagem.setSubject(email.getSubject());
        mensagem.setText(email.getBody());
        javaMailSender.send(mensagem);
    }

    public Email emailCadastroUsuario(String email, String usuario, String senha){
        var emailParaUsuario = new Email();
        emailParaUsuario.setTo(email);
        emailParaUsuario.setSubject("Credenciais para Login na EasyManagement");
        emailParaUsuario.setBody("Seja bem vindo ao EasyManagement!\n Uma conta foi criada com o seu endereço de email e aqui estão os seus dados de acesso:\n Login: " + usuario + "\nSenha: " + senha);

        return emailParaUsuario;
    }

}
