package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("홍길동")
 *   company("활빈당")
 *   skills {
 *     soft("A passion for problem solving")
 *     soft("Good communication skills")
 *     hard("Kotlin")
 *   }
 *   languages {
 *     "Korean" level 5
 *     "English" level 3
 *   }
 * }
 */
class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "신지훈"])
    fun name(name: String) {
        val person = introduce {
            name(name)
        }

        person.name shouldBe name
    }

    @ParameterizedTest
    @CsvSource(value = ["홍길동, 활빈당", "신지훈, 넥스트스텝"])
    fun company(name: String, company: String) {
        val person = introduce {
            name(name)
            company(company)
        }

        person.name shouldBe name
        person.company shouldBe company
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    // also, apply, run, let, with
    return PersonBuilder().apply(block).build()
}

class PersonBuilder() {
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

data class Person(val name: String, val company: String)
