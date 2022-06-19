package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class SkillTest : StringSpec({

    "기술은 여러 개의 Soft와 Hard 기술을 가진다." {
        val skills = skills {
            hardSkill("CS")
            softSkill("Community")
        }

        skills[0].type shouldBe SkillType.HARD
        skills[0].name shouldBe "CS"
        skills[1].type shouldBe SkillType.SOFT
        skills[1].name shouldBe "Community"
    }
})
