package com.rule.evaluator.repository

import com.rule.evaluator.entity.RunEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RunRepository : JpaRepository<RunEntity, Long>{
}