package dev.todaystock.api.common.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional


@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "localhost")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Collect API 테스트")
class CollectTest @Autowired constructor(
    private var mockMvc: MockMvc,
    private var mapper: ObjectMapper
){
    @Test
    fun validateToken() {
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN3Z3NAZ21haWwuY29tIiwiYXV0aCI6IlJPTEVfTWVtYmVyIiwiaWF0IjoxNzMyNTQ5Nzg0LCJleHAiOjE3MzI1OTI5ODR9.YAITPUay04MGspj5UqrnB7roCZCl52f8XombXv0HnDM"
        val request = MockMvcRequestBuilders.get("/v1/collect")
            .header("Authorization", "Bearer $token")
        val result = mockMvc.perform(request)
            .andExpect(status().isOk)
            .andReturn()
    }
}