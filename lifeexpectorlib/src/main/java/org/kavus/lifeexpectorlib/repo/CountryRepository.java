package org.kavus.lifeexpectorlib.repo;

import org.kavus.lifeleftlib.ntt.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CountryRepository extends JpaRepository<Country,Long>, JpaSpecificationExecutor<Country> {
}
