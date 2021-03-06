package com.toob.koot.unitfour.mockito.service

import com.toob.koot.unitfour.mockito.Task


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2021-06-01
 * @email: <a href="mailto:thabo@anylytical.co.za">Anylytical Technologies</a>
 *         <a href="mailto:tl.matjuda@gmail.com">Personal GMail</a>
 */

interface IFourthMockitoService {

    fun doWorkHere(): String

    fun fetchAllTasks(): List<Task>

}
