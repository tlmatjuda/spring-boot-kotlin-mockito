package com.toob.koot.unitfive.mockito.api

import com.toob.koot.unitfive.mockito.Task
import com.toob.koot.unitfive.mockito.service.FiveMockitoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2021-06-01
 * @email: <a href="mailto:thabo@anylytical.co.za">Anylytical Technologies</a>
 *         <a href="mailto:tl.matjuda@gmail.com">Personal GMail</a>
 */

@RestController
@RequestMapping("/mockito")
class FifthMockController(val fiveMockitoService: FiveMockitoService) {

    @GetMapping("/five/service-call")
    fun invokeServiceLevelOperation(): String {
        return fiveMockitoService.doWorkHere()
    }

    @GetMapping("/five/service/repository-call")
    fun invokeDaoLevelOperation(): String {
        return fiveMockitoService.fetchText()
    }

    @GetMapping("/five/tasks")
    fun fetchAllTasks(): List<Task> {
        val fetchAllTasks = fiveMockitoService.fetchAllTasks()
        return fetchAllTasks
    }

}
