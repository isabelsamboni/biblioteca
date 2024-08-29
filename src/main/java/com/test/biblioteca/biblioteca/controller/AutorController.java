/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.biblioteca.biblioteca.controller;

import com.test.biblioteca.biblioteca.entity.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/crear")
    public ResponseEntity<String> crearAutor(@RequestBody Autor autor) {
        String sql = "CALL insertar_autor(?, ?)";
        jdbcTemplate.update(sql, autor.getIdAutor(), autor.getNombreAutor());
        return ResponseEntity.ok("Autor creado exitosamente");
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        String sql = "CALL actualizar_autor(?, ?)";
        jdbcTemplate.update(sql, id, autor.getNombreAutor());
        return ResponseEntity.ok("Autor actualizado exitosamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarAutor(@PathVariable Long id) {
        String sql = "CALL eliminar_autor(?)";
        jdbcTemplate.update(sql, id);
        return ResponseEntity.ok("Autor eliminado exitosamente");
    }
}
