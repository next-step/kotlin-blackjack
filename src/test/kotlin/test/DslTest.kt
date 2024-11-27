package test

import dsl.introduce
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["오민혁", "nooblette"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person =
            introduce {
                name(value)
            }

        person.name shouldBe value
    }

    @MethodSource("nameAndCompany")
    @ParameterizedTest
    fun introduce(list: List<String>) {
        val person =
            introduce {
                name(list[0])
                company(list[1])
            }

        person.name shouldBe list[0]
        person.company shouldBe list[1]
    }

    companion object {
        @JvmStatic
        private fun nameAndCompany() =
            listOf(
                listOf("오민혁", "SSG"),
                listOf("nooblette", "SSG"),
            )
    }
}
