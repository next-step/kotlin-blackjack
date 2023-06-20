package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ValueSource(strings = ["홍길동", "제이슨"])
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
            name("홍길동")
            company("우아한형제들")
        }
        assertThat(person.name).isEqualTo("홍길동")
        assertThat(person.company).isEqualTo("우아한형제들")
    }

    @Test
    fun skills() {
        val person = introduce {
            skills {
                soft("soft")
                hard("hard")
            }
        }
        assertThat(person.skills[0]).isEqualTo(Skill.Soft("soft"))
        assertThat(person.skills[1]).isEqualTo(Skill.Hard("hard"))
    }

    @Test
    fun languages() {
        val person = introduce {
            languages {
                "Korea" level 5
                "English" level 3
            }
        }
        assertThat(person.languages[0]).isEqualTo(Language("Korea", 5))
        assertThat(person.languages[1]).isEqualTo(Language("English", 3))
    }
}
