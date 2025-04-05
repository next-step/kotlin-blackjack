package dsl

import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class DslTest : FunSpec({
    test("name") {
        listOf("Sun", "Jason").forAll {
            val person: Person =
                introduce {
                    name(it)
                }

            person.name shouldBe it
        }
    }

    test("with company") {
        val person: Person =
            introduce {
                name("Sun")
                company("Delivery Hero")
            }

        person shouldBe Person("Sun", "Delivery Hero")
    }
})
