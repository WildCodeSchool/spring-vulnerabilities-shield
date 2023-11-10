package fr.wcs.vulns;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VulnsTestRepository extends JpaRepository<VulnsEntity, Long> {

    @Query("FROM VulnsEntity where id > :minId")
    List<VulnsEntity> getRecentVulns(@Param("minId") Long minId);

    VulnsEntity findByName(String name);


}
