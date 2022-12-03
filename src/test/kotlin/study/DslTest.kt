package study

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
introduce {
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
 */

internal class DslTest {

    @Test
    fun intro() {
        val person: Person = introduce {
            name("황재우")
            company("Google")
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

        person.name shouldBe "황재우"

        person.company shouldBe "Google"

        person.skills.skillList shouldContainExactlyInAnyOrder listOf(
            SoftSkill("A passion for problem solving"),
            SoftSkill("Good communication skills"),
            HardSkill("Kotlin")
        )

        person.languages shouldContainExactly listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}
