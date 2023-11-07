package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["박재성", "제이슨"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            this.name(name)
        }
        person.name shouldBe name
    }

    @Test
    fun person() {
        val person = introduce {
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

        person.name shouldBe "박재성"
        person.company shouldBe "우아한형제들"
        person.skills[0].type shouldBe SkillType.SOFT
        person.skills[2].description shouldBe "Kotlin"
        person.languages[0].name shouldBe "Korean"
        person.languages[1].level shouldBe 3
    }
}
