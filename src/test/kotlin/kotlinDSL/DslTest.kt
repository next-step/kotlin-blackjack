package kotlinDSL

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["jaylene", "제이린"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @ValueSource(strings = ["카카오"])
    @ParameterizedTest
    fun company(value: String) {
        val person = introduce {
            name("jaylene")
            company("카카오")
        }
        assertThat(person.company).isEqualTo(value)
    }

    @Test
    fun skills() {
        val person = introduce {
            name("jaylene")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.skills.soft).containsExactly(
            Soft("A passion for problem solving"),
            Soft("Good communication skills")
        )
        assertThat(person.skills.hard).containsExactly(Hard("Kotlin"))
    }

    @Test
    fun languages() {
        val person = introduce {
            name("jaylene")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages).containsExactly(Language("Korean", 5), Language("English", 3))
    }
}
