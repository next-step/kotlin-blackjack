package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["김호준", "쿠키"])
    @ParameterizedTest
    fun introduce(name: String) {
        val person = introduce {
            name(name)
        }
        assertThat(person.name).isEqualTo(name)
    }

    @Test
    fun company() {
        val person = introduce {
            name("김호준")
            company("회사")
        }
        assertThat(person.name).isEqualTo("김호준")
        assertThat(person.company).isEqualTo("회사")
    }

    @Test
    fun skill() {
        val person = introduce {
            name("김호준")
            company("회사")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.name).isEqualTo("김호준")
        assertThat(person.company).isEqualTo("회사")
        assertThat(person.skills).isEqualTo(
            listOf(
                Skill("A passion for problem solving", SkillType.SOFT),
                Skill("Good communication skills", SkillType.SOFT),
                Skill("Kotlin", SkillType.HARD)
            )
        )
    }

    @Test
    fun languages() {
        val person = introduce {
            name("김호준")
            company("회사")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages(
                listOf(
                    Language("Korean", 5),
                    Language("English", 3)
                )
            )
        }
        assertThat(person.name).isEqualTo("김호준")
        assertThat(person.company).isEqualTo("회사")
        assertThat(person.skills).isEqualTo(
            listOf(
                Skill("A passion for problem solving", SkillType.SOFT),
                Skill("Good communication skills", SkillType.SOFT),
                Skill("Kotlin", SkillType.HARD)
            )
        )
        assertThat(person.languages).isEqualTo(
            listOf(
                Language("Korean", 5),
                Language("English", 3)
            )
        )
    }
}
