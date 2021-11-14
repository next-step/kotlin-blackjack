package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person = introduce("Laco")
        assertThat(person.name).isEqualTo("Laco")
    }

    @Test
    fun company() {
        val person = introduce("Laco") {
            company = "PRND컴퍼니"
        }
        assertThat(person.company).isEqualTo("PRND컴퍼니")
    }

    @Test
    fun soft() {
        val person = introduce("Laco") {
            skills {
                soft("A passion for problem solving")
            }
        }
        val actual = Soft("A passion for problem solving") in person.skills
        assertThat(actual).isTrue
    }

    @Test
    fun hard() {
        val person = introduce("Laco") {
            skills {
                soft("A passion for problem solving")
                hard("Kotlin")
            }
        }
        val actual = Hard("Kotlin") in person.skills
        assertThat(actual).isTrue
    }

    @Test
    fun languages() {
        val person = introduce("Laco") {
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        val actual = Language("Korean", 5) in person.languages
        assertThat(actual).isTrue
    }
}

