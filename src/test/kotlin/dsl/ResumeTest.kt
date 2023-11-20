package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ResumeTest:StringSpec({

    "이력서 dsl 생성 테스트" {
        val result = introduce {
            name("박재성")
            company("우아한형제들")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        result shouldBe Resume(
            name = "박재성",
            company = "우아한형제들",
            skills = Skill(
                softSkills = mutableListOf("A passion for problem solving", "Good communication skills"),
                hardSkills = mutableListOf("Kotlin")
            ),
            languages = Language(
                languages = mutableMapOf(
                    "Korean" to 5,
                    "English" to 3
                )
            )
        )
    }
})