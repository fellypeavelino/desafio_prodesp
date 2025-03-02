package com.prodesp.prodesp.config;


import com.prodesp.prodesp.entities.Categorias;
import com.prodesp.prodesp.entities.Usuarios;
import com.prodesp.prodesp.repositories.CategoriasRepository;
import com.prodesp.prodesp.repositories.UsuariosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
@Component
public class DatabaseInitializer implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);
    
    @Autowired
    private UsuariosRepository usuarioRepository;
    
    @Autowired
    private CategoriasRepository categoriasRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            Usuarios defaultUsuario = new Usuarios();
            defaultUsuario.setLoguin("admin");
            defaultUsuario.setSenha("admin123");
            usuarioRepository.save(defaultUsuario);
            logger.info("Usuário padrão inserido no banco de dados.");
        } else {
            logger.warn("Usuários já existentes no banco de dados.");
        }
        if (categoriasRepository.count() == 0) {
            String[] statusCategoria = {"Em espera", "Priorizado", "Iniciado", "Concluído"};
            for (String string : statusCategoria) {
                Categorias c = new Categorias();
                c.setCor("#24dba4");
                c.setNome(string);
                categoriasRepository.save(c);
            }
        } else {
            logger.warn("Categorias já existentes no banco de dados.");
        }
    }
}
