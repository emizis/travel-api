package com.example.travel.controller;
import com.example.travel.dto.*; import com.example.travel.model.Destino; import com.example.travel.service.DestinoService;
import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import java.net.URI; import java.util.List;
@RestController @RequestMapping("/api/destinos")
public class DestinoController {
    private final DestinoService service;
    public DestinoController(DestinoService s){this.service=s;}
    @PostMapping
    public ResponseEntity<Destino> criar(@RequestBody CreateDestinoRequest r){
        Destino d=service.criarDestino(r.getNome(),r.getLocalizacao(),r.getDescricao(),r.getPreco());
        return ResponseEntity.created(URI.create("/api/destinos/"+d.getId())).body(d);}
    @GetMapping public List<Destino> listar(){return service.listarTodos();}
    @GetMapping("/search")
    public List<Destino> pesq(@RequestParam(required=false)String n,@RequestParam(required=false)String l){
        return service.pesquisar(n,l);}
    @GetMapping("/{id}") public Destino det(@PathVariable Long id){return service.buscarPorId(id);}
    @PutMapping("/{id}")
    public Destino atualizar(@PathVariable Long id,@RequestBody UpdateDestinoRequest r){
        return service.atualizar(id,r.getNome(),r.getLocalizacao(),r.getDescricao(),r.getPreco());}
    @PatchMapping("/{id}/avaliacao")
    public Destino aval(@PathVariable Long id,@RequestBody AvaliacaoRequest r){
        return service.avaliar(id,r.getNota());}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> del(@PathVariable Long id){service.deletar(id); return ResponseEntity.noContent().build();}
}