package com.example.travel.controller;

import com.example.travel.dto.AvaliacaoRequest;
import com.example.travel.dto.CreateDestinoRequest;
import com.example.travel.dto.UpdateDestinoRequest;
import com.example.travel.model.Destino;
import com.example.travel.service.DestinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

    private final DestinoService service;

    public DestinoController(DestinoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Destino> criar(@RequestBody CreateDestinoRequest request) {
        Destino d = service.criarDestino(
                request.getNome(),
                request.getLocalizacao(),
                request.getDescricao(),
                request.getPreco()
        );
        return ResponseEntity.created(URI.create("/api/destinos/" + d.getId())).body(d);
    }

    @GetMapping
    public List<Destino> listar() {
        return service.listarTodos();
    }

    @GetMapping("/search")
    public List<Destino> pesquisar(@RequestParam(required = false) String n,
                                   @RequestParam(required = false) String l) {
        return service.pesquisar(n, l);
    }

    @GetMapping("/{id}")
    public Destino detalhes(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Destino atualizar(@PathVariable Long id, @RequestBody UpdateDestinoRequest request) {
        return service.atualizar(
                id,
                request.getNome(),
                request.getLocalizacao(),
                request.getDescricao(),
                request.getPreco()
        );
    }

    @PatchMapping("/{id}/avaliacao")
    public Destino avaliar(@PathVariable Long id, @RequestBody AvaliacaoRequest request) {
        return service.avaliar(id, request.getNota());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
