package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
        val person = introduce {
            name("김민지")
        }

        assertThat(person.name).isEqualTo("김민지")
    }

    @Test
    fun company() {
        val person = introduce {
            name("김민지")
            company("KT")
        }

        assertThat(person.company).isEqualTo("KT")
    }

    @Test
    fun soft() {
        val person = introduce {
            name("김민지")
            company("KT")
            skills {
                soft("A passion for problem solving")
            }
        }

        assertThat(person.skills).contains(Soft("A passion for problem solving"))
    }

    @Test
    fun hard() {
        val person = introduce {
            name("김민지")
            company("KT")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.skills).contains(Hard("Kotlin"))
    }

    @Test
    fun languages() {
        val person = introduce {
            name("김민지")
            company("KT")
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
        assertThat(person.languages).contains(Korean(5))
        assertThat(person.languages).contains(English(3))
    }
}
