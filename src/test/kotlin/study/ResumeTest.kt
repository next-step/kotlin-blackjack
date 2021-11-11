package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import study.domain.Hard
import study.domain.Language
import study.domain.Soft
import study.domain.introduce

class ResumeTest {
    @Test
    fun name() {
        val person = introduce {
            name("장세훈")
        }
        assertThat(person.name).isEqualTo("장세훈")
    }

    @Test
    fun company() {
        val person = introduce {
            name("장세훈")
            company("회사")
        }
        assertThat(person.company).isEqualTo("회사")
    }

    @Test
    fun soft() {
        val person = introduce {
            name("장세훈")
            company("회사")
            skills {
                soft("passion")
            }
        }
        assertThat(person.skills.skills).contains(Soft("passion"))
    }

    @Test
    fun hard() {
        val person = introduce {
            name("장세훈")
            company("회사")
            skills {
                soft("passion")
                hard("Kotlin")
            }
        }
        assertThat(person.skills.skills).contains(Hard("Kotlin"))
    }

    @Test
    fun language() {
        val person = introduce {
            name("장세훈")
            company("회사")
            skills {
                soft("passion")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
            }
        }
        assertThat(person.languages.toList()).containsExactlyInAnyOrder(
            Language("Korean", 5)
        )
    }
}





