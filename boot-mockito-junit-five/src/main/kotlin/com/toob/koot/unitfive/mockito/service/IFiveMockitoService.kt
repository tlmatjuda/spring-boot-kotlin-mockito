package com.toob.koot.unitfive.mockito.service

import com.toob.koot.unitfive.mockito.Task


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2021-06-01
 * @email: <a href="mailto:thabo@anylytical.co.za">Anylytical Technologies</a>
 *         <a href="mailto:tl.matjuda@gmail.com">Personal GMail</a>
 */

interface IFiveMockitoService {

    fun doWorkHere(): String

    fun fetchAllTasks(): List<Task>

}
