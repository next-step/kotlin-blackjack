package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["이홍구", "홍길동"])
    @ParameterizedTest
    fun name(nameParameter: String) {
        val person = introduce {
            name(nameParameter)
            company("카카오")
        }

        person.name shouldBe nameParameter
    }

    @Test
    fun company() {
        val person = introduce {
            name("이홍구")
            company("카카오")
        }
        person.name shouldBe "이홍구"
        person.company shouldBe "카카오"
    }

    @Test
    fun skills() {
        val softSkillName = "부드러운 육체"
        val hardSkillName = "강건한 정신"
        val person = introduce {
            name("이홍구")
            company("카카오")
            skills {
                soft(softSkillName)
                hard(hardSkillName)
            }
        }
        person.skills.size shouldBe 2
        person.skills[0].type shouldBe SkillType.Soft
        person.skills[0].description shouldBe softSkillName
        person.skills[1].type shouldBe SkillType.Hard
        person.skills[1].description shouldBe hardSkillName
    }

    @Test
    fun languages() {
        val person = introduce {
            name("이홍구")
            company("카카오")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        person.languages.size shouldBe 2
        person.languages[0].name shouldBe "Korean"
        person.languages[0].level shouldBe 5
        person.languages[1].name shouldBe "English"
        person.languages[1].level shouldBe 3
    }
}
