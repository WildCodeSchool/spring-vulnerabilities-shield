package fr.wcs.vulns;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TestRepository extends JpaRepository<TestEntity, Long> {

    @Query("FROM TestEntity where id > :minId")
    List<TestEntity> getRecent(@Param("minId") Long minId);

    TestEntity findByName(String name);


}
