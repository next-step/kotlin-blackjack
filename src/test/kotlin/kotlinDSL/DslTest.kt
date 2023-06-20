package kotlinDSL

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("박재성")
 *   company("우아한형제들")
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
    @ValueSource(strings = ["홍길동", "김태훈"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            this.name(value)
        }
        person.name shouldBe value
    }

    @Test
    fun company() {
        val person: Person = introduce {
            this.name("김태훈")
            this.company("컴파니")
        }
        person.name shouldBe "김태훈"
        person.company shouldBe "컴파니"
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

    fun company(value: String) {
        company = value
    }

    fun build(): Person {
        return Person(name, company)
    }
}