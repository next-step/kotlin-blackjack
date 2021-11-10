import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import study.Hard
import study.Language
import study.Soft
import study.introduce

class ResumeTest {
    @Test
    fun name() {
        val person = introduce {
            name("나채원")
            company("위대한상상")
        }

        assertThat(person.name).isEqualTo("나채원")
        assertThat(person.company).isEqualTo("위대한상상")
    }

    @Test
    fun company() {
        val person = introduce {
            name("나채원")
            company("위대한상상")
        }

        assertThat(person.company).isEqualTo("위대한상상")
    }

    @Test
    fun softSkill() {
        val person = introduce {
            name("나채원")
            company("위대한상상")
            skills {
                soft("A passion for problem solving")
                hard("Python")
            }
        }

        assertThat(person.skills.skills).contains(Soft("A passion for problem solving"))
    }

    @Test
    fun hardSkill() {
        val person = introduce {
            name("나채원")
            company("위대한상상")
            skills {
                soft("A passion for problem solving")
                hard("Python")
            }
        }

        assertThat(person.skills).contains(Hard("Python"))
    }

    @Test
    fun language() {
        val person = introduce {
            name("나채원")
            company("위대한상상")
            skills {
                soft("A passion for problem solving")
                hard("Python")
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
