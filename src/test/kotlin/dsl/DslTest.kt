package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["디디", "루루", "라"])
    fun dsl(input: String) {
        val company = "우아한형제들"
        val person: Person = introduce {
            name(input)
            company(company)
        }

        assertThat(person.name).isEqualTo(input)
        assertThat(person.company).isEqualTo(company)
    }

    private fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String

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

data class Person(
    val name: String,
    val company: String
)
