package com.EjercicioAyudantia.ISoft.controller;

import com.EjercicioAyudantia.ISoft.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static List<Task> tasks = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Map<String, String> body) {
        Task task = new Task(
            body.get("titulo"),
            body.get("prioridad"),
            body.get("fechaLimite")
        );
        tasks.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
        @RequestParam(required = false) String prioridad,
        @RequestParam(required = false) String titulo,
        @RequestParam(required = false) String fechaLimite
    ) {
        List<Task> resultado = new ArrayList<>();

        for (Task t : tasks) {
            boolean coincide = true;

            if (prioridad != null && !t.getPrioridad().equalsIgnoreCase(prioridad)) {
                coincide = false;
            }
            if (titulo != null && !t.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                coincide = false;
            }
            if (fechaLimite != null && !fechaLimite.equals(t.getFechaLimite())) {
                coincide = false;
            }

            if (coincide) {
                resultado.add(t);
            }
        }

        return ResponseEntity.ok(resultado);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) {
        for (Task t : tasks) {
            if (t.getId().equals(id)) {
                t.setCompletada(true);
                return ResponseEntity.ok(t);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}