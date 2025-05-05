package dsl

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainAll
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
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }

        assertSoftly(person.skills) {
            values.size shouldBe 3
            values.filterIsInstance<Skill.Soft>().size shouldBe 2
            values.filterIsInstance<Skill.Hard>().size shouldBe 1
        }
    }

    test("with languages") {
        val person: Person =
            introduce {
                name("Sun")
                languages {
                    "Korean" level 5
                    "English" level 4
                }
            }

        assertSoftly(person.languages) {
            values.size shouldBe 2
            values shouldContainAll listOf(Language("Korean", 5), Language("English", 4))
        }
    }

    test("all properties") {
        val person: Person =
            introduce {
                name("Sun")
                company("Delivery Hero")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
                languages {
                    "Korean" level 5
                    "English" level 4
                }
            }

        assertSoftly(person) {
            name shouldBe "Sun"
            company shouldBe "Delivery Hero"
            skills.values.size shouldBe 3
            skills.values.filterIsInstance<Skill.Soft>().size shouldBe 2
            skills.values.filterIsInstance<Skill.Hard>().size shouldBe 1
            languages.values.size shouldBe 2
        }
    }
})
