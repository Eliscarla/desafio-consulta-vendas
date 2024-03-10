package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportProjection;
import com.devsuperior.dsmeta.projections.SumaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(nativeQuery = true, value =   "SELECT tb_sales.ID,       " +
            "        tb_sales.DATE,                     " +
            "        tb_sales.AMOUNT,                   " +
            "        tb_seller.NAME                    " +
            "FROM tb_sales                            " +
            "INNER JOIN tb_seller ON (tb_seller.ID = tb_sales.SELLER_ID)          " +
            "WHERE tb_sales.DATE BETWEEN :DATA_INICIAL AND :DATA_FINAL    " +
            "  AND  CASE WHEN coalesce(:NAME, 'Empty') <> 'Empty' THEN " +
            "       UPPER(tb_seller.NAME) LIKE UPPER(CONCAT('%', :NAME, '%'))    " +
            "                    ELSE                                                           " +
            "                    (1 = 1) END   ",
            countQuery = "SELECT COUNT(tb_sales.ID)                                " +
                    "FROM tb_sales                                                  " +
                    "INNER JOIN tb_seller ON (tb_seller.ID = tb_sales.SELLER_ID)          " +
                    "WHERE tb_sales.DATE BETWEEN :DATA_INICIAL AND :DATA_FINAL      " +
                    "  AND  CASE WHEN coalesce(:NAME, 'Empty') <> 'Empty' THEN  " +
                    "       UPPER(tb_seller.NAME) LIKE UPPER(CONCAT('%', :NAME, '%'))    " +
                    "                    ELSE                                                           " +
                    "                    (1 = 1) END   "
    )
    Page<ReportProjection> getReport(LocalDate DATA_INICIAL, LocalDate DATA_FINAL, String NAME, Pageable pageable);

    @Query(nativeQuery = true, value =  "SELECT  SUM(tb_sales.AMOUNT) AS AMOUNT, " +
            " tb_seller.NAME " +
            "FROM tb_sales " +
            "INNER JOIN tb_seller ON (tb_sales.SELLER_ID = tb_seller.ID) " +
            "WHERE tb_sales.DATE BETWEEN :DATA_INICIAL AND :DATA_FINAL " +
            "GROUP BY tb_seller.NAME")
    List<SumaryProjection> getSumary (LocalDate DATA_INICIAL, LocalDate DATA_FINAL);
}
