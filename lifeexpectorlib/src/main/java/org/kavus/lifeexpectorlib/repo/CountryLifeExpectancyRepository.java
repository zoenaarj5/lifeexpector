package org.kavus.lifeexpectorlib.repo;

import org.kavus.lifeleftlib.ntt.CountryLifeExpectancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CountryLifeExpectancyRepository extends JpaRepository<CountryLifeExpectancy,Long>, JpaSpecificationExecutor<CountryLifeExpectancy> {
}
