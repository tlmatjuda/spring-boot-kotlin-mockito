package com.toob.koot.unitfive.mockito.service

import com.toob.koot.unitfive.mockito.Task
import com.toob.koot.unitfive.mockito.dao.FiftMockitoRepository
import org.springframework.stereotype.Service


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2021-06-01
 * @email: <a href="mailto:thabo@anylytical.co.za">Anylytical Technologies</a>
 *         <a href="mailto:tl.matjuda@gmail.com">Personal GMail</a>
 */

@Service
class FiveMockitoService (
    val fiftMockitoRepository: FiftMockitoRepository
    ) : IFiveMockitoService {


    override fun doWorkHere(): String {
        return "I Serve With JUNIT 5 & Pure Mockito"
    }

    override fun fetchAllTasks(): List<Task> {
        return fiftMockitoRepository.findAllTasks()
    }

    fun fetchText(): String {
        return fiftMockitoRepository.findText()
    }

}
