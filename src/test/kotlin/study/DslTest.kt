import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import study.*

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["옥종훈", "theo"])
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person = introduce {
            name("theo")
            company("kakao")
        }
        person.name shouldBe "theo"
        person.company shouldBe "kakao"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("theo")
            company("kakao")
            skills {
                soft("java")
                soft("python")
                hard("kotlin")
            }
        }
        person.name shouldBe "theo"
        person.company shouldBe "kakao"
        person.skills shouldBe Skills(
            listOf(
                SoftSkill("java"),
                SoftSkill("python"),
                HardSkill("kotlin"))
        )
    }

    @Test
    fun languages() {
        val person = introduce {
            name("theo")
            company("kakao")
            skills {
                soft("java")
                soft("python")
                hard("kotlin")
            }
            languages {
                "Korean" level 5
                "Japanese" level 4
                "English" level 3
            }
        }
        person.name shouldBe "theo"
        person.company shouldBe "kakao"
        person.skills shouldBe Skills(
            listOf(
                SoftSkill("java"),
                SoftSkill("python"),
                HardSkill("kotlin"))
        )
        person.languages shouldBe Languages(
            listOf(
                Language("Korean", 5),
                Language("Japanese", 4),
                Language("English", 3)
            )
        )
    }

}