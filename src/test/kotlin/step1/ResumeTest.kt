package step1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
        val person = introduce {
            name("김병준")
        }
        assertThat(person.name).isEqualTo("김병준")
    }

    @Test
    fun company() {
        val person = introduce {
            name("김병준")
            company("XX")
        }
        assertThat(person.company).isEqualTo("XX")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("김병준")
            company("XX")
            skills {
                soft("joke")
                hard("eat")
            }
        }
        assertThat(person.skills).contains(Soft("joke"))
        assertThat(person.skills).contains(Hard("eat"))
    }

    @Test
    fun languages() {
        val person = introduce {
            name("김병준")
            company("XX")
            skills {
                soft("joke")
                hard("eat")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages["Korean"]).isEqualTo(5)
        assertThat(person.languages["English"]).isEqualTo(3)
    }
}
