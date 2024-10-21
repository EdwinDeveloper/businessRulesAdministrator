package com.rule.evaluator.repository

import com.rule.evaluator.entity.EvaluatorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EvaluatorRepository : JpaRepository<EvaluatorEntity, Long>{
}