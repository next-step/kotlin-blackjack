package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PersonKtTest : StringSpec({
    "introduce name" {
        forAll(
            row("박재성"),
            row("제이슨"),
        ) {
            val person = introduce {
                name(it)
            }

            person.name shouldBe it
        }
    }

    "introduce name and company" {
        val person = introduce {
            name("박재성")
            company("우아한형제들")
        }

        person shouldBe Person("박재성", "우아한형제들")
    }
})
