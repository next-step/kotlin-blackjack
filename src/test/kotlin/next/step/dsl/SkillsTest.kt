package next.step.dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SkillsTest {
    @Test
    fun skills() {
        val skills: Skills = skills {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }
        
        skills.soft shouldBe setOf("A passion for problem solving", "Good communication skills")
        skills.hard shouldBe setOf("Kotlin")
    }
}