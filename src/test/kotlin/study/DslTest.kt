package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "이승환", "어쩌구"])
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }

        person.name shouldBe value
        person.company shouldBe null
    }

    @Test
    fun company() {
        val person = introduce {
            name("이승환")
            company("카카오")
        }

        person.name shouldBe "이승환"
        person.company shouldBe "카카오"
    }
}

class Person(val name: String, val company: String?)

class PersonBuilder {
    lateinit var name: String
    var company: String? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build(): Person {
        return Person(name, company)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
