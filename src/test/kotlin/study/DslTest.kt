package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest : StringSpec({


    @ValueSource(strings = ["김자바", "최코틀"])
    @ParameterizedTest
    fun nameTest(input: String) {
        val person = introduce {
            name(input)
        }
        person.name shouldBe input
    }
})
