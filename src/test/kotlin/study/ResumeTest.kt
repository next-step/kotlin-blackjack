package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person = introduce {
            name("ssibongee")
        }
        assertThat(person.name).isEqualTo("ssibongee")
    }

    @Test
    fun company() {
        val person = introduce {
            name("ssibongee")
            company("Channel Corp")
        }
        assertThat(person.company).isEqualTo("Channel Corp")
    }

    @Test
    fun soft() {
        val person = introduce {
            name("ssibongee")
            company("Channel Corp")
            skills {
                soft("A passion for problem solving")
            }
        }
        assertThat(person.skills.skills).contains(Soft("A passion for problem solving"))
    }

    @Test
    fun hard() {
        val person = introduce {
            name("ssibongee")
            company("Channel Corp")
            skills {
                soft("A passion for problem solving")
                hard("Kotlin")
            }
        }

        assertThat(person.skills).contains(Hard("Kotlin"))
    }

    @Test
    fun language() {
        val person = introduce {
            name("ssibongee")
            company("Channel Corp")
            skills {
                soft("A passion for problem solving")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.languages).contains(Language("Korean", 5))
        assertThat(person.languages).contains(Language("English", 3))
    }
}
