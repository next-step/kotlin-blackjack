package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import study.resume.Hard
import study.resume.Language
import study.resume.Soft
import study.resume.introduce

class ResumeTest {

    @Test
    fun name() {
        val person = introduce {
            name("임한결")
        }
        assertThat(person.name).isEqualTo("임한결")
    }

    @Test
    fun company() {
        val person = introduce {
            name("임한결")
            company("엑스트라이버")
        }
        assertThat(person.company).isEqualTo("엑스트라이버")
    }

    @Test
    fun soft() {
        val person = introduce {
            name("임한결")
            company("엑스트라이버")
            skills {
                soft("A passion for problem solving")
            }
        }
        assertThat(person.skills).contains(Soft("A passion for problem solving"))
    }

    @Test
    fun hard() {
        val person = introduce {
            name("임한결")
            company("엑스트라이버")
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
            name("임한결")
            company("엑스트라이버")
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
            Language("English", 3),
        )
    }
}
