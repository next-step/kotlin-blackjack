package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import study.language.Language
import study.language.Languages
import study.skill.Skill

class DslTest {
    @ValueSource(strings = ["허성원", "홍길동"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("허성원")
            company("아시아나")
        }
        assertThat(person.name).isEqualTo("허성원")
        assertThat(person.company).isEqualTo("아시아나")
    }

    @Test
    fun skill() {
        val person = introduce {
            name("허성원")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        val skill = Skill(
            listOf("A passion for problem solving", "Good communication skills"),
            listOf("Kotlin")
        )

        assertThat(person.skill).isEqualTo(skill)
    }

    @Test
    fun language() {
        val person = introduce {
            name("허성원")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        val languages = Languages(listOf(Language("Korean", 5), Language("English", 3)))

        assertThat(person.languages).isEqualTo(languages)
    }
}
