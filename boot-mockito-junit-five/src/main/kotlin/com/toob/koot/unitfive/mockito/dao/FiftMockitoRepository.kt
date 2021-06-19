package com.toob.koot.unitfive.mockito.dao

import com.toob.koot.unitfive.mockito.Task
import org.springframework.stereotype.Repository


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2021-06-01
 * @email: <a href="mailto:thabo@anylytical.co.za">Anylytical Technologies</a>
 *         <a href="mailto:tl.matjuda@gmail.com">Personal GMail</a>
 */

@Repository
interface FiftMockitoRepository {

    fun findText(): String {
        return "I Found JUNIT 5 Mockito Text From Database"
    }

    fun findAllTasks(): List<Task>{
        val listOf = listOf(
            Task(1, "Do A Demo For Hadi Nazari With Unit 5"),
            Task(2, "Explain The Demo On Unit 5")
        )
        return listOf
    }
}
