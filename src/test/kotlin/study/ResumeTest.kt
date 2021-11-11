package study

import dslstudy.Hard
import dslstudy.Language
import dslstudy.Soft
import dslstudy.introduce
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
        val person = introduce {
            name("최상용")
        }

        assertThat(person.name).isEqualTo("최상용")
    }

    @Test
    fun company() {
        val person = introduce {
            name("최상용")
            company("버킷플레이스")
        }

        assertThat(person.company).isEqualTo("버킷플레이스")
    }

    @Test
    fun soft() {
        val person = introduce {
            name("최상용")
            company("버킷플레이스")
            skills {
                soft("A passion for problem solving")
            }
        }

        assertThat(person.skills).contains(Soft("A passion for problem solving"))
    }

    @Test
    fun hard() {
        val person = introduce {
            name("최상용")
            company("버킷플레이스")
            skills {
                soft("A passion for problem solving")
                hard("sleep")
            }
        }

        assertThat(person.skills).contains(Hard("sleep"))
    }

    @Test
    fun languages() {
        val person = introduce {
            name("최상용")
            company("버킷플레이스")
            skills {
                soft("A passion for problem solving")
                hard("sleep")
            }
            languages {
                "Korean" level 5
                "English" level 1
            }
        }

        assertThat(person.languages).contains(Language("Korean", 5))
        assertThat(person.languages).contains(Language("English", 1))
    }
}
