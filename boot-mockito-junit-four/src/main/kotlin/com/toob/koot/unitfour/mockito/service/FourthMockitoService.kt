package com.toob.koot.unitfour.mockito.service

import com.toob.koot.unitfour.mockito.Task
import com.toob.koot.unitfour.mockito.dao.FourthMockitoRepository
import org.springframework.stereotype.Service


/**
 * @author: Thabo Lebogang Matjuda
 * @since: 2021-06-01
 * @email: <a href="mailto:thabo@anylytical.co.za">Anylytical Technologies</a>
 *         <a href="mailto:tl.matjuda@gmail.com">Personal GMail</a>
 */

@Service
class FourthMockitoService (val fourthMockitoRepository: FourthMockitoRepository): IFourthMockitoService {


    override fun doWorkHere(): String {
        return "I Serve With JUNIT 4 & Pure Mockito"
    }

    override fun fetchAllTasks(): List<Task> {
        return fourthMockitoRepository.findAllTasks()
    }

    fun fetchText(): String {
        return fourthMockitoRepository.findText()
    }

}
