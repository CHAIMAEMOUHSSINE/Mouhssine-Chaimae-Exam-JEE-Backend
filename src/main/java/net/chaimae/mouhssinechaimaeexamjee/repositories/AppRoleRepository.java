package net.chaimae.mouhssinechaimaeexamjee.repositories;

import ma.insurance.backend.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByRoleName(String roleName);
}

