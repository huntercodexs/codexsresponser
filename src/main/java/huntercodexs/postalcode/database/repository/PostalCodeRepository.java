package huntercodexs.postalcode.database.repository;

import huntercodexs.postalcode.database.model.PostalCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCodeEntity, Long> {
    @Query(value = "SELECT * from postalcode WHERE cep = ?1", nativeQuery = true)
    PostalCodeEntity findByCep(String cep);
}
