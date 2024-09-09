package com.emazon.stock.domain.usecases.ArticuloImpl;

import com.emazon.stock.domain.exception.ArticuloCategoriaRepetidaException;
import com.emazon.stock.domain.exception.ArticuloConExcesoCategoriasException;
import com.emazon.stock.domain.exception.ArticuloConFaltaDeCategoriasException;
import com.emazon.stock.domain.exception.UpdatingStockException;
import com.emazon.stock.domain.model.Articulo;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.SupplyLog;
import com.emazon.stock.domain.puertos.in.ArticuloUseCases;
import com.emazon.stock.domain.puertos.out.ArticuloRepositoryPort;
import com.emazon.stock.domain.puertos.out.SupplyLogPersistencePort;
import com.emazon.stock.domain.puertos.out.SupplyServicePort;
import com.emazon.stock.domain.usecases.ArticuloImpl.validators.ArticuloValidator;
import com.emazon.stock.domain.util.PaginationCustom;
import com.emazon.stock.domain.util.PaginationParams;
import com.github.javafaker.Cat;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.emazon.stock.domain.util.Constantes.ARTICULO_MAXIMO_CATEGORIAS;
import static com.emazon.stock.domain.util.Constantes.ARTICULO_MINIMO_CATEGORIAS;


@RequiredArgsConstructor
public class ArticuloUseCasesImpl implements ArticuloUseCases {

    private final ArticuloRepositoryPort articuloRepositoryPort;
    private final ArticuloValidator articuloValidator;
    private final SupplyServicePort supplyServicePort;
    private final SupplyLogPersistencePort supplyLogPersistencePort;

    private final String SUPPLY_LOG_STATUS_ON_RECEIVED= "PENDING";
    private final String SUPPLY_LOG_STATUS_ON_CONFIRMED = "CONFIRMED";

    @Override
    public PaginationCustom listArticles(PaginationParams paginationParams) {
        return articuloRepositoryPort.listArticles(paginationParams);
    }

    @Override
    public Optional<Articulo> findArticleById(Long articleId) {
        return articuloRepositoryPort.findArticleById(articleId);
    }

    @Override
    public void updateArticleStock(Long supplyId, Long articleId, int quantity) throws InterruptedException {
        boolean supplyExists = isSupplyLogPresentInPersistence(supplyId);
        boolean articleExists = isArticlePresentInPersistence(articleId);

        if (supplyExists) {
            supplyServicePort.communicateSupplyReceived(supplyId);
            return;
        }

        if (!articleExists) {
            supplyServicePort.communicateSupplyRejected(supplyId);
            return;
        }

        try{
            articuloRepositoryPort.updateArticleStock(articleId, quantity);
            supplyLogPersistencePort.createSupplyLog(createSupplyLog(supplyId, articleId));
            supplyServicePort.communicateSupplyReceived(supplyId);
        }catch (UpdatingStockException ex){
            throw new RuntimeException("Failed to update article stock", ex);
        }

    }



    private boolean isArticlePresentInPersistence(Long articleId) {
        Optional<Articulo> article = articuloRepositoryPort.findArticleById(articleId);
        return article.isPresent();
    }


    private boolean isSupplyLogPresentInPersistence(Long supplyId){
        Optional<SupplyLog> supplyLogOpt = supplyLogPersistencePort.findSupplyLogBySupplyId(supplyId);
        return supplyLogOpt.isPresent();
    }

    private SupplyLog createSupplyLog(Long supplyId, Long articleId){
        SupplyLog supplyLog = new SupplyLog();
        supplyLog.setSupplyId(supplyId);
        supplyLog.setArticle(new Articulo(articleId));
        supplyLog.setStatus(SUPPLY_LOG_STATUS_ON_RECEIVED);
        supplyLog.setReceivedAt(LocalDateTime.now());
        return supplyLog;
    }

    @Override
    public Articulo saveArticulo(Articulo articulo) {
        articuloValidator.saveArticleValidate(articulo);
        return articuloRepositoryPort.saveArticulo(articulo);
    }

}
