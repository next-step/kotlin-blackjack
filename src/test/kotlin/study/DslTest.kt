package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

/**
 * introduce {
 * name("김영태")
 * company("홍익대학교")
 * skills {
 * soft("A passion for problem solving")
 * soft("Good communication skills")
 * hard("Kotlin")
 * }
 * languages {
 * "Korean" level 5
 * "English" level 3
 * }
 * }
 */
class DslTest {
    @ParameterizedTest
    @CsvSource(value = ["홍길동,활빈당", "김영태,홍익대"])
    fun test(name: String, company: String) {
        val person = introduce {
            name(name)
            company(company)
        }

        person.name shouldBe name
        person.company shouldBe company
    }
}

fun introduce(block: Person.() -> Unit): Person = Person().apply(block)

class Person() {
    lateinit var name: String
    lateinit var company: String

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }
}
