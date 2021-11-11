package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
        val person = introduce {
            name("김진엽")
        }
        assertThat(person.name).isEqualTo("김진엽")
    }

    @Test
    fun company() {
        val person = introduce {
            name("김진엽")
            company("드림포라")
        }
        assertThat(person.company).isEqualTo("드림포라")
    }

    @Test
    fun soft() {
        val person = introduce {
            name("김진엽")
            company("드림포라")
            skills {
                soft("A passion for problem solving")
            }
        }
        assertThat(person.skills).contains(Soft("A passion for problem solving"))
    }

    @Test
    fun hard() {
        val person = introduce {
            name("김진엽")
            company("드림포라")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.skills).contains(Hard("Kotlin"))
    }

    @Test
    fun language() {
        val person = introduce {
            name("김진엽")
            company("드림포라")
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
        assertThat(person.languages).contains(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}
