package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
        val person = introduce {
            name("etff")
        }
        assertThat(person.name).isEqualTo("etff")
    }

    @Test
    fun company() {
        val person = introduce {
            name("etff")
            company("test1")
        }
        assertThat(person.company).isEqualTo("test1")
    }

    @Test
    fun soft() {
        val person = introduce {
            name("etff")
            company("test1")
            skills {
                soft("A passion for problem solving")
            }
        }
        assertThat(person.skills).contains(Soft("A passion for problem solving"))
    }

    @Test
    fun hard() {
        val person = introduce {
            name("etff")
            company("test1")
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
            name("etff")
            company("test1")
            skills {
                soft("A passion for problem solving")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
            }
        }
        assertThat(person.language).contains(Language("Korean", 5))
    }
}
