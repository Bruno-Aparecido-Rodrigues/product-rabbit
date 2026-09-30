package io.github.fatec.repository;

import io.github.fatec.entity.Produto;
import io.github.fatec.repository.adapter.ProdutoRepositoryImplAdapter;
import io.github.fatec.repository.client.ProdutoRepositoryWithMongodb;
import io.github.fatec.repository.orm.ProdutoOrm;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {
    private final ProdutoRepositoryWithMongodb repository;

    public ProdutoRepositoryImpl(ProdutoRepositoryWithMongodb repository) {
        this.repository = repository;
    }

    @Override
    public Produto save(Produto produto) {
        try {
            ProdutoOrm orm = repository.save(ProdutoRepositoryImplAdapter.cast(produto));
            return ProdutoRepositoryImplAdapter.cast(orm);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Produto update(Produto produto) {
        try {
            if (!repository.existsById(produto.id())) {
                throw new RuntimeException("Produto não encontrado: " + produto.id());
            }
            ProdutoOrm orm = repository.save(ProdutoRepositoryImplAdapter.cast(produto));
            return ProdutoRepositoryImplAdapter.cast(orm);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            Optional<ProdutoOrm> optional = repository.findByIdAndAtivoTrue(id);
            if (optional.isEmpty()) {
                throw new RuntimeException("Produto não encontrado: " + id);
            }
            ProdutoOrm inativo = new ProdutoOrm(
                    optional.get().id(),
                    optional.get().nome(),
                    optional.get().descricao(),
                    optional.get().imagens(),
                    optional.get().preco(),
                    optional.get().estoque(),
                    optional.get().avaliacao(),
                    false);
            repository.save(inativo);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Produto> findAll() {
        try {
            return repository.findByAtivoTrue().stream()
                    .map(ProdutoRepositoryImplAdapter::cast)
                    .toList();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Produto findById(String id) {
        try {
            Optional<ProdutoOrm> optional = repository.findByIdAndAtivoTrue(id);
            if (optional.isEmpty()) {
                throw new RuntimeException("Produto não encontrado: " + id);
            }
            return ProdutoRepositoryImplAdapter.cast(optional.get());
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
