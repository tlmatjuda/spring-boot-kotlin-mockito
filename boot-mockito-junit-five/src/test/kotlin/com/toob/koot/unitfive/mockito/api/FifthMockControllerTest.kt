package com.toob.koot.unitfive.mockito.api

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.toob.koot.unitfive.mockito.Task
import com.toob.koot.unitfive.mockito.dao.FiftMockitoRepository
import com.toob.koot.unitfive.mockito.service.FiveMockitoService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


/**
 * We are using JUNit 5.x So in that case we should use @RunWith
 */
@ExtendWith(MockitoExtension::class)


/**
 * Tell Spring to Bootup only the Web Layer sicne we are testing the controllers.
 * So we can mock up the rest
 */
@WebMvcTest
class FifthMockControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    lateinit var dao: FiftMockitoRepository

    @SpyBean
    lateinit var service: FiveMockitoService

    @Test
    fun `Test That, We Can Invoke REST With Mocked Service, Mocked String Response`() {
        val EXPECTED_RESPONSE_TEXT = "Test Case Mocked Service Response using Junit 5"
        Mockito.`when`(service.doWorkHere()).thenReturn(EXPECTED_RESPONSE_TEXT)

        mvc.perform(
            MockMvcRequestBuilders.get("/mockito/five/service-call"))
            .andExpect( MockMvcResultMatchers.status().isOk)
            .andExpect( MockMvcResultMatchers.content().string(EXPECTED_RESPONSE_TEXT))
            .andReturn()

        Mockito.verify(service, Mockito.times(1)).doWorkHere()
    }

    @Test
    fun `Test That, We Can Invoke REST With Mocked Service & DAO, Mocked Custom Object List`() {

        // Mock up a response list.
        val EXPECTED_MOCKED_LIST = listOf(
            Task(1, "First Mocked Task for JUNIT 5"),
            Task(2, "Second Mocked Task for JUNIT 5")
        )

        Mockito.`when`(dao.findAllTasks()).thenReturn(EXPECTED_MOCKED_LIST)

        val httpResponse = mvc.perform(
            MockMvcRequestBuilders.get("/mockito/five/tasks")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)

        // We expect this to be an array since it's a List from Kotlin Side
        httpResponse.andExpect( MockMvcResultMatchers.jsonPath("$").isArray)

        // The Json Structure and Values we expected as a whole
        val EXPECTED_MOCKED_LIST_AS_JSON_TEXT  = toJsonText(EXPECTED_MOCKED_LIST)
        httpResponse.andExpect( MockMvcResultMatchers.content().json( EXPECTED_MOCKED_LIST_AS_JSON_TEXT, true))
    }

    @Throws( JsonProcessingException::class)
    private fun toJsonText( anyJson: Any): String {
        val mapper = jacksonObjectMapper()
        val writeValueAsString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(anyJson)
        return writeValueAsString
    }

}
