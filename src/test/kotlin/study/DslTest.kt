package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun languages() {
        val person = introduce {
            name("이동철")
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

        assertThat(person.name).isEqualTo("이동철")
        assertThat(person.company).isEqualTo("회사")
        assertThat(person.skills.skills[0].desc).isEqualTo("A passion for problem solving")
        assertThat(person.skills.skills[1].desc).isEqualTo("Good communication skills")
        assertThat(person.skills.skills[2].desc).isEqualTo("Kotlin")
        assertThat(person.languages.value["Korean"]).isEqualTo(5)
        assertThat(person.languages.value["English"]).isEqualTo(3)
    }
}
