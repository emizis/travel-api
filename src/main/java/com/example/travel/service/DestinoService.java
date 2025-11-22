package com.example.travel.service;

import com.example.travel.model.Destino;
import com.example.travel.repository.DestinoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DestinoService {

    private final DestinoRepository repository;

    public DestinoService(DestinoRepository repository) {
        this.repository = repository;
    }

    public Destino criarDestino(String nome, String localizacao, String descricao, Double preco) {
        Destino d = new Destino(nome, localizacao, descricao, preco);
        return repository.save(d);
    }

    public List<Destino> listarTodos() {
        return repository.findAll();
    }

    public Destino buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destino não encontrado"));
    }

    public List<Destino> pesquisar(String nome, String localizacao) {
        boolean hasNome = nome != null && !nome.isBlank();
        boolean hasLoc = localizacao != null && !localizacao.isBlank();

        if (hasNome && hasLoc) {
            return repository.findByNomeContainingIgnoreCaseAndLocalizacaoContainingIgnoreCase(nome, localizacao);
        } else if (hasNome) {
            return repository.findByNomeContainingIgnoreCase(nome);
        } else if (hasLoc) {
            return repository.findByLocalizacaoContainingIgnoreCase(localizacao);
        } else {
            return listarTodos();
        }
    }

    public Destino atualizar(Long id, String nome, String localizacao, String descricao, Double preco) {
        Destino d = buscarPorId(id);
        d.setNome(nome);
        d.setLocalizacao(localizacao);
        d.setDescricao(descricao);
        d.setPreco(preco);
        return repository.save(d);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Destino não encontrado");
        }
        repository.deleteById(id);
    }

    public Destino avaliar(Long id, int nota) {
        if (nota < 1 || nota > 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nota deve ser entre 1 e 10");
        }
        Destino d = buscarPorId(id);
        double total = d.getNotaMedia() * d.getQuantidadeAvaliacoes();
        int novaQuantidade = d.getQuantidadeAvaliacoes() + 1;
        double novaMedia = (total + nota) / novaQuantidade;

        d.setQuantidadeAvaliacoes(novaQuantidade);
        d.setNotaMedia(Math.round(novaMedia * 100.0) / 100.0);

        return repository.save(d);
    }
}
