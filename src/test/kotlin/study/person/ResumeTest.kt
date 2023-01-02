package study.person

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ResumeTest {
    @ValueSource(strings = ["허지훈", "modernflow"])
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
            name("허지훈")
            company("엑스트라이버")
        }
        assertThat(person.name).isEqualTo("허지훈")
        assertThat(person.company).isEqualTo("엑스트라이버")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("허지훈")
            company("엑스트라이버")
            skills {
                soft("leadership")
                soft("communication")
                hard("football")
            }
        }
        assertThat(person.skills.skills.size).isEqualTo(3)
        assertThat(person.skills.skills.filter { it.level == Level.SOFT }.size).isEqualTo(2)
        assertThat(person.skills.skills.filter { it.level == Level.HARD }.size).isEqualTo(1)
    }

    @Test
    fun languages() {
        val person = introduce {
            name("허지훈")
            company("엑스트라이버")
            skills {
                soft("leadership")
                soft("communication")
                hard("football")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages!!.languages.size).isEqualTo(2)
        assertThat(person.languages!!.languages.single { it.name == "Korean" }.level).isEqualTo(5)
        assertThat(person.languages!!.languages.single { it.name == "English" }.level).isEqualTo(3)
    }
}
