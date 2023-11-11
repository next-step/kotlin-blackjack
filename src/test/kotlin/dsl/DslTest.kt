package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DslTest : StringSpec({

    "introduce Test" {
        val name = "박재성"
        val company = "우아한형제들"
        val person = introduce {
            name(name)
            company(company)
        }

        person.name shouldBe name
        person.company shouldBe company
    }
})
