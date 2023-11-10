package fr.wcs.vulns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

@Repository
public class UnsafeTestRepository {

    @Autowired
    EntityManager entityManager;

    public List<TestEntity> getFromNameUnsafe(String name) {
        String query = "FROM TestEntity WHERE name = '" + name + "'";
        System.out.println("will execute unsafe query 😅 : " + query);
        return entityManager.createQuery(query).getResultList();
    }
}
