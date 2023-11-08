package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ValueSource(strings = ["김자바", "최코틀"])
    @ParameterizedTest
    fun test(input: String) {
        val person = introduce {
            name(input)
            company("양산박")
        }
        person.name shouldBe input
        person.company shouldBe "양산박"
    }
}

fun introduce(block: Person.() -> Unit): Person {
    return Person().apply(block)
}

data class Person(
    var name: String = "",
    var company: String = "",
) {
    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }
}
