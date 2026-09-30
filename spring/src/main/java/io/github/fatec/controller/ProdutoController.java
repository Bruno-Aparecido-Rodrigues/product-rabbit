package io.github.fatec.controller;

import io.github.fatec.controller.adapter.ProdutoControllerAdapter;
import io.github.fatec.controller.request.ProdutoRequest;
import io.github.fatec.controller.request.ProdutoUpdateRequest;
import io.github.fatec.controller.response.ProdutoResponse;
import io.github.fatec.entity.Produto;
import io.github.fatec.event.AtualizarVendaPublicador;
import io.github.fatec.event.dto.AtualizarVendaEvento;
import io.github.fatec.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fatec/produto")
public class ProdutoController {

    private final ProdutoRepository repository;
    private final AtualizarVendaPublicador publicador;

    public ProdutoController(ProdutoRepository repository, AtualizarVendaPublicador publicador) {
        this.repository = repository;
        this.publicador = publicador;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public ProdutoResponse save(@RequestBody ProdutoRequest request) {
        Produto saved = repository.save(ProdutoControllerAdapter.cast(request));
        publicador.publicar(new AtualizarVendaEvento(saved.id(), saved.estoque()));
        return toResponse(saved);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    public ProdutoResponse update(@RequestBody ProdutoUpdateRequest request) {
        Produto updated = repository.update(ProdutoControllerAdapter.cast(request));
        publicador.publicar(new AtualizarVendaEvento(updated.id(), updated.estoque()));
        return toResponse(updated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        repository.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<ProdutoResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProdutoResponse findById(@PathVariable String id) {
        Produto produto = repository.findById(id);
        return toResponse(produto);
    }

    private ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
                produto.id(),
                produto.nome(),
                produto.descricao(),
                produto.imagens(),
                produto.preco(),
                produto.estoque(),
                produto.avaliacao(),
                produto.ativo());
    }
}