package code.adagedo.repository;

import code.adagedo.models.StreamData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StreamRepository extends JpaRepository<StreamData, UUID> {
}

