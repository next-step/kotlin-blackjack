package study

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ValueSource(strings = ["신성수", "씨유", "제이슨", "브라운", "포비"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }

        person.name shouldBe value
    }

    @Test
    fun company() {
        val person = introduce {
            name("신성수")
            company("우아한형제들")
        }

        person.name shouldBe "신성수"
        person.company shouldBe "우아한형제들"
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("신성수")
            company("우아한형제들")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "신성수"
        person.company shouldBe "우아한형제들"
        person.skills shouldContainExactly listOf(
            SoftSkill("A passion for problem solving"),
            SoftSkill("Good communication skills"),
            HardSkill("Kotlin")
        )
    }

    @Test
    fun languages() {
        val person = introduce {
            name("신성수")
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

        person.name shouldBe "신성수"
        person.company shouldBe "우아한형제들"
        person.skills shouldContainExactly listOf(
            SoftSkill("A passion for problem solving"),
            SoftSkill("Good communication skills"),
            HardSkill("Kotlin")
        )

        person.language shouldContainExactly listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}
