package com.rule.admin.Repository;

import com.rule.admin.Entity.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<RuleEntity, Long> {
}

