package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DslLearn : StringSpec({

    "DSL 기본 문법을 학습한다" {
        listOf("김자바", "최코틀").forEach { input: String ->
            val person = introduce {
                name(input)
                company("양산박")
            }
            person.name shouldBe input
            person.company shouldBe "양산박"
        }
    }
})

fun introduce(block: Person.() -> Unit): Person {
    return Person().apply(block)
}

data class Person(
    var name: String = "",
    var company: String = "",
) {
    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }
}
