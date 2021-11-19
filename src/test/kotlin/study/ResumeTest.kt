package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person = introduce {
            name("서승환")
        }
        assertThat(person.name).isEqualTo("서승환")
    }

    @Test
    fun company() {
        val person = introduce {
            name("서승환")
            company("Algorigo")
        }
        assertThat(person.company).isEqualTo("Algorigo")
    }

    @Test
    fun soft() {
        val person = introduce {
            name("서승환")
            company("Algorigo")
            skills {
                soft("A passion for problem solving")
            }
        }
        assertThat(person.skills.skills).contains(Soft("A passion for problem solving"))
    }

    @Test
    fun hard() {
        val person = introduce {
            name("서승환")
            company("Algorigo")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("kotlin")
            }
        }
        assertThat(person.skills).contains(Hard("kotlin"))
    }

    @Test
    fun language() {
        val person = introduce {
            name("서승환")
            company("Algorigo")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages).contains(
            Language("Korean", 5),
            Language("English", 3),
        )
    }
}
