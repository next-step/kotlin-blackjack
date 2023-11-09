package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("홍길동")
 *   company("활빈당")
 *   skills {
 *      soft("A passion for problem solving")
 *      soft("Good communication skills")
 *      hard("Kotlin")
 *   }
 *   languages {
 *      "Korean" level 5
 *      "English" level 3
 *   }
 */
class DslTest {
    @ValueSource(strings = ["홍길동", "조재연"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("조재연")
            company("Google")
        }
        assertThat(person.name).isEqualTo("조재연")
        assertThat(person.company).isEqualTo("Google")
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
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
