/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.biblioteca.biblioteca.controller;


import com.test.biblioteca.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Endpoint para insertar un libro
    @PostMapping("/crear")
    public ResponseEntity<String> crearLibro(@RequestParam Long idLibro, @RequestParam String tituloLibro, @RequestParam Long idAutor) {
        libroService.insertarLibro(idLibro, tituloLibro, idAutor);
        return ResponseEntity.ok("Libro creado exitosamente");
    }

    // Endpoint para actualizar un libro
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarLibro(@PathVariable Long id, @RequestParam String tituloLibro, @RequestParam Long idAutor) {
        libroService.actualizarLibro(id, tituloLibro, idAutor);
        return ResponseEntity.ok("Libro actualizado exitosamente");
    }

    // Endpoint para eliminar un libro
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.ok("Libro eliminado exitosamente");
    }

    // Endpoint para consultar todos los libros
    @GetMapping("/consultar")
    public ResponseEntity<List<Map<String, Object>>> consultarLibros() {
        List<Map<String, Object>> libros = libroService.consultarLibros();
        return ResponseEntity.ok(libros);
    }
}
