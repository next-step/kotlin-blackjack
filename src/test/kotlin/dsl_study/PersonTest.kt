package dsl_study

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PersonTest : StringSpec({
    "introduce" {
        listOf("김경록", "로키").forAll {
            val person = introduce {
                name(it)
            }

            person.name shouldBe it
        }
    }

    "company" {
        val person = introduce {
            name("김경록")
            company("맘편한세상")
        }

        person.name shouldBe "김경록"
        person.company shouldBe "맘편한세상"
    }
})
