package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslStudy {

    @ValueSource(strings = ["알렉스", "제이슨", "홍길동"])
    @ParameterizedTest
    fun name(str: String) {
        val person = introduce1 {
            name(str)
            this
        }

        person.name shouldBe str
    }

    @Test
    fun company() {
        val person = introduce1 {
            name("알렉스")
            company("CJ올리브영")
        }

        person.name shouldBe "알렉스"
        person.company shouldBe "CJ올리브영"
    }

    @Test
    fun pair() {
        val pair1 = Pair(1, "one")
        val pair2 = 2 to "two"
        val pair3 = 3 of "three"
    }
}

// languages {
//    "Korean" level 5
//    "English" level 3
// }
infix fun Int.of(str: String): Pair<Int, String> = Pair(this, str)

// fun introduce(block: Person.() -> Person): Person {
//    return Person().block()
// }
fun introduce1(block: Person.() -> Unit): Person {
    // also, apply, run, let, with
    return Person().apply(block) // block 함수를 인자로 그대로 넘기기
//    val person = Person()
//    person.block()
//    return person
}

class Person1 {
    lateinit var name: String
    lateinit var company: String

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }
}
