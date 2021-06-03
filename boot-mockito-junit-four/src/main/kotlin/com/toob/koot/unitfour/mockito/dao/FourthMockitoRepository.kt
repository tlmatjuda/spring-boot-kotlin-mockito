package com.toob.koot.unitfour.mockito.dao

import com.toob.koot.unitfour.mockito.Task
import org.springframework.stereotype.Repository


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2021-06-01
 * @email: <a href="mailto:thabo@anylytical.co.za">Anylytical Technologies</a>
 *         <a href="mailto:tl.matjuda@gmail.com">Personal GMail</a>
 */

@Repository
interface FourthMockitoRepository {

    fun findText(): String {
        return "I Found JUNIT 4 Mockito Text From Database"
    }

    fun findAllTasks(): List<Task>{
        val listOf = listOf(
            Task(1, "Do A Demo For Hadi Nazari"),
            Task(2, "Explain The Demo")
        )
        return listOf
    }
}
