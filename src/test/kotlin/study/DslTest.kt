package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import study.Person.Companion.introduce

class DslTest : StringSpec({

    "introduce" {
        val person: Person = introduce {
            name("손재빈")
        }

        person.name shouldBe "손재빈"
    }

    "company" {
        val person: Person = introduce {
            name("손재빈")
            company("인프랩")
        }

        person.company shouldBe "인프랩"
    }

    "skills" {
        val person: Person = introduce {
            name("손재빈")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.skills shouldBe listOf(
            Skill(SkillType.SOFT, "A passion for problem solving"),
            Skill(SkillType.SOFT, "Good communication skills"),
            Skill(SkillType.HARD, "Kotlin")
        )
    }

    "language" {
        val person: Person = introduce {
            name("손재빈")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        person.languages shouldBe listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
})
