package com.example.projectdemo.repository;

import com.example.projectdemo.model.entity.Information;
import com.example.projectdemo.model.type.InformationStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InformationRepository extends JpaRepository<Information, Integer> {

    @Query("select i from Information i where i.category.id =:categoryId and i.urlIn = :urlIn and i.delFlag = 1")
    Optional<Information> existsByCategoryIdAndUrlIn(@Param("categoryId") Integer categoryId, @Param("urlIn") String urlIn);

    @Query("select i from Information i where i.category.id =:categoryId and i.urlIn = :urlOut and i.delFlag = 1")
    Optional<Information> existsByCategoryIdAndUrlOut(@Param("categoryId") Integer categoryId, @Param("urlOut") String urlOut);

    @Query("select i from Information i where i.category.id =:categoryId and i.urlIn = :urlIn and i.id <> :id and i.delFlag = 1")
    Optional<Information> existsByCategoryIdAndUrlInEdit(@Param("categoryId") Integer categoryId, @Param("urlIn") String urlIn, @Param("id") Integer id);

    @Query("select i from Information i where i.category.id =:categoryId and i.urlOut = :urlOut and i.id <> :id and i.delFlag = 1")
    Optional<Information> existsByCategoryIdAndUrlOutEdit(@Param("categoryId") Integer categoryId, @Param("urlOut") String urlOut, @Param("id") Integer id);

    @Query("select i from Information i where i.status = :status and i.delFlag = 1")
    List<Information> getAllByStatus(@Param("status") InformationStatusType status);

    List<Information> getAllByAccount_Id(Integer id);

    Integer countAllByCategoryId(Integer id);
}
