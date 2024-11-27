package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class DslTest : StringSpec({
    "name" {
        listOf("모두한", "모두", "모").forAll { name ->
            val person =
                introduce {
                    name(name)
                }
            person.name shouldBe name
        }
    }

    "company" {
        val person =
            introduce {
                name("모두한")
                company("레벨13")
            }
        person.company shouldBe "레벨13"
    }

    "soft skills" {
        val person =
            introduce {
                name("모두한")
                skills {
                    soft("Good communication skills")
                    soft("a passion for problem solving")
                }
            }
        person.skills?.softSkills?.get(0) shouldBe "Good communication skills"
        person.skills?.softSkills?.get(1) shouldBe "a passion for problem solving"
    }

    "hard skills" {
        val person =
            introduce {
                name("모두한")
                skills {
                    hard("Kotlin")
                    hard("Spring")
                }
            }
        person.skills?.hardSkills?.get(0) shouldBe "Kotlin"
        person.skills?.hardSkills?.get(1) shouldBe "Spring"
    }

    "soft and hard skills" {
        val person =
            introduce {
                name("모두한")
                skills {
                    soft("Good communication skills")
                    soft("a passion for problem solving")
                    hard("Kotlin")
                    hard("Spring")
                }
            }
        person.skills?.softSkills?.get(0) shouldBe "Good communication skills"
        person.skills?.softSkills?.get(1) shouldBe "a passion for problem solving"
        person.skills?.hardSkills?.get(0) shouldBe "Kotlin"
        person.skills?.hardSkills?.get(1) shouldBe "Spring"
    }

    "languages" {
        val person =
            introduce {
                name("모두한")
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }

        person.languages?.get("Korean") shouldBe 5
        person.languages?.get("English") shouldBe 3
    }
})
