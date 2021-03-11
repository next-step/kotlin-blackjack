package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person: Person = introduce {
            name("정현수")
            company("마리트")
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

        assertThat(person.name).isEqualTo("정현수")
        assertThat(person.company).isEqualTo("마리트")
        assertThat(person.skills.toList()).containsAll(
            listOf(
                Soft("A passion for problem solving"),
                Soft("Good communication skills"),
                Hard("Kotlin")
            )
        )
        assertThat(person.languages.toList()).containsAll(
            listOf(
                Language("Korean", 5),
                Language("English", 3)
            )
        )
    }
}
