package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

/**
 * introduce {
 *   name("박경철")
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
class DslTest : StringSpec({
    "사람 이름을 설정한다." {
        listOf(
            "박경철" to "박경철",
            "홍길동" to "홍길동",
        )
            .forAll { (name, expected) ->
                introduce { name(name) }.name shouldBe expected
            }
    }

    "사람, 회사 이름을 설정한다." {
        val person = introduce {
            name("박경철")
            company("우아한형제들")
        }

        person.company shouldBe "우아한형제들"
    }
})

private fun introduce(builder: PersonBuilder.() -> Unit): Person =
    PersonBuilder().apply(builder).build()

private class Person(val name: String, val company: String?)

private class PersonBuilder {
    lateinit var name: String
    var company: String? = null

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun build(): Person = Person(this.name, this.company)
}

