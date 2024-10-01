package com.rule.evaluator.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "run")
class RunEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    val id: Long? = null,

    @Column(name = "flow")
    val flow: String,

    @Column(name = "input")
    val input: String? = null,

    @Column(name = "response")
    var response: String? = null,

    @Column(name = "rule_id")
    var ruleId: String? = null,

    @Column(name = "traceability_id")
    val traceabilityId: UUID? = null,

    @Column(name = "user_id")
    val userId: UUID,

    @CreationTimestamp
    @Column(name = "created_at", unique = true)
    var createdAt: LocalDateTime? = null

    )