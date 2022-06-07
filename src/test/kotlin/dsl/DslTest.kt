package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["이동욱", "jojoldu"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("이동욱")
            company("인프랩")
        }
        assertThat(person.name).isEqualTo("이동욱")
        assertThat(person.company).isEqualTo("인프랩")
    }

    @Test
    fun skill() {
        val expectSoft1 = "A passion for problem solving"
        val expectSoft2 = "Good communication skills"
        val expectHard = "Kotlin"

        val person: Person = introduce {
            skills {
                soft(expectSoft1)
                soft(expectSoft2)
                hard(expectHard)
            }
        }

        assertThat(person.skills!!.getSoft(0).title).isEqualTo(expectSoft1)
        assertThat(person.skills!!.getSoft(1).title).isEqualTo(expectSoft2)
        assertThat(person.skills!!.getHard(0).title).isEqualTo(expectHard)
    }

    @Test
    fun language() {
        val person: Person = introduce {
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.languages!!.get(0).name).isEqualTo("Korean")
        assertThat(person.languages!!.get(0).level).isEqualTo(5)
        assertThat(person.languages!!.get(1).name).isEqualTo("English")
        assertThat(person.languages!!.get(1).level).isEqualTo(3)
    }
}
