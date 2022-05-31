package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    
    @ValueSource(strings = ["이동욱", "jojoldu"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }
    
    @Test
    fun company() {
        val person: Person = introduce {
            name("이동욱")
            company("인프랩")
        }
        assertThat(person.name).isEqualTo("이동욱")
        assertThat(person.company).isEqualTo("인프랩")
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    
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

data class Person(var name: String, var company: String) {
    fun name(value: String) {
        name = value
    }
    
    fun company(value: String) {
        company = value
    }
}
