package com.rule.evaluator.config

import org.postgresql.util.PSQLException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.EnableRetry
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import java.sql.SQLException
import javax.sql.DataSource

@Service
@EnableRetry
class DatabaseService @Autowired constructor(private val dataSource: DataSource) {

    @Retryable(value = [PSQLException::class, SQLException::class], maxAttempts = 5, backoff = Backoff(delay = 2000))
    @Throws(SQLException::class)
    fun connectToDatabase() {
        try {
            dataSource.connection.use { connection ->
                println("Connected to the database successfully!")
            }
        } catch (e: SQLException) {
            println("Caught SQLException: ${e.message}")
            throw e  // Ensure exception is rethrown for retry logic
        } catch (e: PSQLException) {
            println("Caught PSQLException: ${e.message}")
            throw e
        }
    }
}