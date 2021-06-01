package com.toob.koot.unitfour.mockito.api

import com.toob.koot.unitfour.mockito.service.FourthMockitoService
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
class FourthMockController(val fourthMockitoService: FourthMockitoService) {

    @GetMapping("/junit-four")
    fun invokeServiceLevelOperation(): String {
        return fourthMockitoService.doWorkHere()
    }

    @GetMapping("/junit-four")
    fun invokeDaoLevelOperation(): String {
        return fourthMockitoService.fetchText()
    }

}
