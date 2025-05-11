package com.service.HomeInsurance.repository;

import com.service.HomeInsurance.entity.HomePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomePolicyRepository extends JpaRepository<HomePolicy,Long> {
}
