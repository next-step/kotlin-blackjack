package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun introduce() {
        val person = introduce("신동윤") {
            company("kakao")
            skills {
                soft("JPA")
                hard("Kotlin")
            }
            languages {
                "English" level 3
                "Korean" level 5
            }
        }
        assertThat(person.name).isEqualTo("신동윤")
        assertThat(person.company).isEqualTo("kakao")
        assertThat(person.skills).contains(Skill.Soft("JPA"))
        assertThat(person.languages).contains(Language("English", 3))
    }
}
