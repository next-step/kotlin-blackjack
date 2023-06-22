package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ValueSource(strings = ["홍길동", "김대현"])
    @ParameterizedTest
    fun introduce(name: String) {
        val person: Person = introduce {
            name(name)
        }
        person.name shouldBe name
        person.company shouldBe null
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(val name: String, val company: String?)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun build(): Person {
        return Person(name, company)
    }
}