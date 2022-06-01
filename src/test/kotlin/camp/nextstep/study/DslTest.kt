package camp.nextstep.study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class DslTest {

    @ParameterizedTest
    @ValueSource(
        strings = ["엄현식", "Tim"]
    )
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }

        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("엄현식")
            company("카카오")
        }

        assertThat(person.name).isEqualTo("엄현식")
        assertThat(person.company).isEqualTo("카카오")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("엄현식")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.skills.getHardSkills().size()).isEqualTo(1)
        assertThat(person.skills.getSoftSkills().size()).isEqualTo(2)

        val hardSkillValues = person.skills.getHardSkills().skills.map { it.description }
        val softSkillValues = person.skills.getSoftSkills().skills.map { it.description }

        assertThat(hardSkillValues.contains("Kotlin")).isTrue
        assertThat(softSkillValues.contains("A passion for problem solving")).isTrue
        assertThat(softSkillValues.contains("Good communication skills")).isTrue
    }

    @Test
    fun languages() {
        val person = introduce {
            name("엄현식")
            company("카카오")
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

        assertThat(person.languages.size()).isEqualTo(2)
        assertThat(person.languages["Korean"]!!.level).isEqualTo(5)
        assertThat(person.languages["English"]!!.level).isEqualTo(3)
    }
}

