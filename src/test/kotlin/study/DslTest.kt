package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 * name("박세준")
 * company("에스이랩")
 * skills {
 * soft("A passion for problem solving")
 * soft("Good communication skills")
 * hard("Kotlin")
 * }
 * languages {
 * "Korean" level 5
 * "Japanese" level 3
 * "English" level 1
 * }
 * }
 */
class DslTest {
    @ValueSource(strings = ["홍길동", "박세준"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
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

class Person(val name: String, val company: String? = null)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null

    fun name(name: String) {
        this.name = name
    }

    fun company(value: String) {
        company = value
    }

    fun build(): Person {
        return Person(name, company)
    }
}
