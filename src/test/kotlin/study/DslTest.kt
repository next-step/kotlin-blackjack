package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
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
    @ValueSource(strings = ["홍길동", "수퍼비"])
    @ParameterizedTest
    fun name(name: String) {
        val person =
            introduce {
                name(name)
            }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
            }
        person.company shouldBe "활빈당"
    }
}

fun introduce(block: Person.() -> Unit): Person {
    return Person().apply(block)
}

class Person {
    lateinit var name: String
    var company: String? = null

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }
}
