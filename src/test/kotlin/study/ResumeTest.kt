package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/*
Introduce.kt {
  name("박재성")
  company("우아한형제들")
  skills {
    soft ("A passion for problem solving")
    soft ("Good communication skills")
    hard ("Kotlin")
  }
  languages {
    "Korean" level 5
    "English" level 3
  }
}
*/

internal class ResumeTest {
    @Test
    fun kotlinDSL() {
        val person = introduce {
            name("오상우")
            company("Google")
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

        assertThat(person.name).isEqualTo("오상우")
        assertThat(person.company).isEqualTo("Google")
        assertThat(person.skills.soft[0]).isEqualTo("A passion for problem solving")
        assertThat(person.skills.soft[1]).isEqualTo("Good communication skills")
        assertThat(person.skills.hard[0]).isEqualTo("Kotlin")
        assertThat(person.languages["Korean"]).isEqualTo(5)
        assertThat(person.languages["English"]).isEqualTo(3)
    }
}
