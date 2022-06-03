package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun languages() {
        val person = introduce {
            name("김성주")
            company("회사")
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

        assertThat(person.name).isEqualTo("김성주")
        assertThat(person.company).isEqualTo("회사")
        assertThat(person.skills.soft[0]).isEqualTo("A passion for problem solving")
        assertThat(person.skills.soft[1]).isEqualTo("Good communication skills")
        assertThat(person.skills.hard).isEqualTo("Kotlin")
        assertThat(person.languages.languages["Korean"]).isEqualTo(5)
        assertThat(person.languages.languages["English"]).isEqualTo(3)
    }
}
