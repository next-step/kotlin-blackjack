package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun introduce() {
        val person: Person = introduce {
            name("김다정")
        }
        person.name shouldBe "김다정"
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("김다정")
            company("Woowa")
        }
        person.name shouldBe "김다정"
        person.company shouldBe "Woowa"
    }
}

fun introduce(function: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(function).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null

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

data class Person(val name: String, val company: String?)
