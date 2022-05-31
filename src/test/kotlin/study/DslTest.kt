package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*
introduce {
    name("박재성")
    company("우아한형제들")
    skills {
        soft("A passion for problem solving")
        soft("Good communication skills")
        hard("Kotlin")
    }
    languages {
        "Korean" level 5
        "English" level 3
    }
}
*/
class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["제이드", "고유식"])
    internal fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }

        assertThat(person.name).isEqualTo(value)
    }

    @ParameterizedTest
    @ValueSource(strings = ["카카오", "티몬"])
    internal fun company(value: String) {
        val person: Person = introduce {
            name("고유식")
            company(value)
        }

        assertThat(person.company).isEqualTo(value)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
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

data class Person(val name: String, val company: String)
