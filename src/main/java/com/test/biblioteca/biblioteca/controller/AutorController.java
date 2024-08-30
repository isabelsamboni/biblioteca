/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.biblioteca.biblioteca.controller;



import com.test.biblioteca.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    // Endpoint para insertar un autor
    @PostMapping("/crear")
    public ResponseEntity<String> crearAutor(@RequestParam Long idAutor, @RequestParam String nombreAutor) {
        autorService.insertarAutor(idAutor, nombreAutor);
        return ResponseEntity.ok("Autor creado exitosamente");
    }

    // Endpoint para actualizar un autor
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarAutor(@PathVariable Long id, @RequestParam String nombreAutor) {
        autorService.actualizarAutor(id, nombreAutor);
        return ResponseEntity.ok("Autor actualizado exitosamente");
    }

    // Endpoint para eliminar un autor
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarAutor(@PathVariable Long id) {
        autorService.eliminarAutor(id);
        return ResponseEntity.ok("Autor eliminado exitosamente");
    }

    // Endpoint para consultar todos los autores
    @GetMapping("/consultar")
    public ResponseEntity<List<Map<String, Object>>> consultarAutores() {
        List<Map<String, Object>> autores = autorService.consultarAutores();
        return ResponseEntity.ok(autores);
    }
}

