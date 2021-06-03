package com.toob.koot.unitfour.mockito.api

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.toob.koot.unitfour.mockito.Task
import com.toob.koot.unitfour.mockito.dao.FourthMockitoRepository
import com.toob.koot.unitfour.mockito.service.FourthMockitoService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


/**
 * AUTHOR: Thabo Matjuda
 * DATE: 03 June 2021
 * DESCRIPTION:
 */

/**
 * We are using JUNit 4.x So in that case we should use @RunWith
 */
@RunWith(SpringJUnit4ClassRunner::class)

/**
 * Tell Spring to Bootup only the Web Layer sicne we are testing the controllers.
 * So we can mock up the rest
 */
@WebMvcTest
class FourthMockControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    lateinit var dao: FourthMockitoRepository

    @SpyBean
    lateinit var service: FourthMockitoService


    @Test
    fun `Test That, We Can Invoke REST With Mocked Service, ( Mocked String Response )`() {
        val EXPECTED_RESPONSE_TEXT = "Test Case Mocked Service Response"
        `when`(service.doWorkHere()).thenReturn(EXPECTED_RESPONSE_TEXT)

        mvc.perform(
            MockMvcRequestBuilders.get("/mockito/junit-four/service-call"))
            .andExpect( status().isOk)
            .andExpect( content().string(EXPECTED_RESPONSE_TEXT))
            .andReturn()

        verify( service, times(1)).doWorkHere()
    }

    @Test
    fun `Test That, We Can Invoke REST With Mocked Service & DAO, ( Mocked Custom Object List )`() {

        // Mock up a response list.
        val EXPECTED_MOCKED_LIST = listOf(
            Task(1, "First Mocked Task"),
            Task(2, "Second Mocked Task")
        )

        `when`(dao.findAllTasks()).thenReturn(EXPECTED_MOCKED_LIST)

        val httpResponse = mvc.perform(
            MockMvcRequestBuilders.get("/mockito/junit-four/tasks")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)

        // We expect this to be an array since it's a List from Kotlin Side
        httpResponse.andExpect( jsonPath("$").isArray)

        // The Json Structure and Values we expected as a whole
        val EXPECTED_MOCKED_LIST_AS_JSON_TEXT  = toJsonText(EXPECTED_MOCKED_LIST)
        httpResponse.andExpect( content().json( EXPECTED_MOCKED_LIST_AS_JSON_TEXT, true))
    }


    /**
     * We use this to convert our mocked objects to JSON String.
     * We do this so that we can Assert / Match it up with the HTTP REST Response we go
     */
    @Throws( JsonProcessingException::class)
    private fun toJsonText( anyJson: Any): String {
        val mapper = jacksonObjectMapper()
        val writeValueAsString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(anyJson)
        return writeValueAsString
    }
}
