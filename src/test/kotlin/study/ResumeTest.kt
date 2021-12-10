package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person = introduce {
            name("조재현")
        }
        assertThat(person.name).isEqualTo("조재현")
    }

    @Test
    fun company() {
        val person = introduce {
            name("조재현")
            company("퇴사..")
        }
        assertThat(person.company).isEqualTo("퇴사..")
    }

    @Test
    fun soft() {
        val person = introduce {
            name("조재현")
            company("퇴사..")
            skills {
                soft("A passion for problem solving")
            }
        }
        assertThat(person.skills).contains(Soft("A passion for problem solving"))
    }

    @Test
    fun languageKorean() {
        val person = introduce {
            name("조재현")
            company("퇴사..")
            skills {
                soft("A passion for problem solving")
                hard("kotlin")
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
