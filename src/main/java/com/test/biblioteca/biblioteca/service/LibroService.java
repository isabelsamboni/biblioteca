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
public class LibroService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para insertar un libro
    public void insertarLibro(Long idLibro, String tituloLibro, Long idAutor) {
        String sql = "CALL insertar_libro(?, ?, ?)";
        jdbcTemplate.update(sql, idLibro, tituloLibro, idAutor);
    }

    // Método para actualizar un libro
    public void actualizarLibro(Long idLibro, String tituloLibro, Long idAutor) {
        String sql = "CALL actualizar_libro(?, ?, ?)";
        jdbcTemplate.update(sql, idLibro, tituloLibro, idAutor);
    }

    // Método para eliminar un libro
    public void eliminarLibro(Long idLibro) {
        String sql = "CALL eliminar_libro(?)";
        jdbcTemplate.update(sql, idLibro);
    }

    // Método para consultar todos los libros
    public List<Map<String, Object>> consultarLibros() {
        String sql = "SELECT * FROM libros";
        return jdbcTemplate.queryForList(sql);
    }
}
