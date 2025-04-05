package dsl

import io.kotest.assertions.assertSoftly
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

    test("with skills") {
        val person: Person =
            introduce {
                name("Sun")
                company("Delivery Hero")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }

        assertSoftly(person.skills) {
            softSkills.size shouldBe 2
            hardSkills.size shouldBe 1
        }
    }
})
