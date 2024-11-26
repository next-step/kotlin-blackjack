package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("안성재")
 *   company("호두랩스")
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
 *
 */
class DslTest {

    @ValueSource(strings = ["안성재"])
    @ParameterizedTest
    fun nameTest(name: String) {
        val person = introduce {
            this.name(name)
        }
        person.name shouldBe name
    }

    @Test
    fun companyTest() {
        val person = introduce {
            this.name("안성재")
            company("호두랩스")
        }
        person.name shouldBe "안성재"
        person.company shouldBe "호두랩스"
    }
}

private fun introduce(block: PersonBuilder.() -> Unit): Person { // Person의 모든 함수를 받도록 설정
    // step1
//    val person = Person("")
//    person.block()
//    return person

    // step2
//    return Person("").apply { block() }

    // step3
//    return Person("").apply(block)

    // step4
//    return PersonBuilder().apply(block)

    // step5
    return PersonBuilder().apply(block).build()
}

// step1
//data class Person(var name: String) {
//    fun name(value: String) {
//        this.name = value
//    }
//}

// step2
//class Person(var name: String = "") {
//    fun name(value: String) {
//        this.name = value
//    }
//}

// --------------------------

// step1
//class Person {
//    lateinit var name: String
//    var company: String? = null // 회사가 없을 수도 있음
//
//    fun name(value: String) {
//        this.name = value
//    }
//
//    fun company(value: String) {
//        this.company = value
//    }
//}



class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null // 회사가 없을 수도 있음

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun build(): Person {
        return Person(name, company)
    }
}

data class Person(val name: String, val company: String?)