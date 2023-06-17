package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SkillsTest : StringSpec({
    "소프트스킬, 하드스킬을 받아 기술을 반환한다" {
        val skills = introduceSkills {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }

        skills shouldBe Skills(
            softSkills = listOf("A passion for problem solving", "Good communication skills"),
            hardSkills = listOf("Kotlin")
        )
    }
})
