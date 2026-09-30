package io.github.fatec.repository.client;

import io.github.fatec.repository.orm.ProdutoOrm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepositoryWithMongodb extends MongoRepository<ProdutoOrm, String> {
    List<ProdutoOrm> findByAtivoTrue();
    Optional<ProdutoOrm> findByIdAndAtivoTrue(String id);
}
