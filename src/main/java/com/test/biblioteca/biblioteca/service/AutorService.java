/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.biblioteca.biblioteca.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class AutorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para insertar un autor
    public void insertarAutor(Long idAutor, String nombreAutor) {
        String sql = "CALL insertar_autor(?, ?)";
        jdbcTemplate.update(sql, idAutor, nombreAutor);
    }

    // Método para actualizar un autor
    public void actualizarAutor(Long idAutor, String nombreAutor) {
        String sql = "CALL actualizar_autor(?, ?)";
        jdbcTemplate.update(sql, idAutor, nombreAutor);
    }

    // Método para eliminar un autor
    public void eliminarAutor(Long idAutor) {
        String sql = "CALL eliminar_autor(?)";
        jdbcTemplate.update(sql, idAutor);
    }

    // Método para consultar todos los autores
    public List<Map<String, Object>> consultarAutores() {
        String sql = "SELECT * FROM TABLE(consultar_autores())";
        return jdbcTemplate.queryForList(sql);
    }
}
