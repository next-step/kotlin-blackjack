package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["홍길동", "김호준"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
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

    @Test
    fun pair() {
        val pair1 = Pair(1, "one")
        val pair2 = 1.to("one")
        val pair3 = 1 to "one"
        val pair4 = 1 of "one"
    }
}

infix fun Int.of(s: String): Pair<Int, String> = Pair(this, s)

fun introduce(block: Person.() -> Unit): Person {
    return Person().apply { block() }
}

class Person {
    lateinit var name: String
    lateinit var company: String

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }
}
