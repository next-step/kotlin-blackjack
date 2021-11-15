package study.resume

import resume.domain.introduce
import resume.domain.skill.Soft
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import resume.domain.language.Language
import resume.domain.skill.Hard

class ResumeTest {
    @Test
    fun name() {
        val person = introduce {
            name("김세현")
        }
        assertThat(person.name).isEqualTo("김세현")
    }

    @Test
    fun company() {
        val person = introduce {
            name("김세현")
            company("inoon")
        }
        assertThat(person.name).isEqualTo("김세현")
    }

    @Test
    fun soft() {
        val person = introduce {
            name("김세현")
            company("inoon")
            skills {
                soft("Solve Problem")
                hard("Kotlin")
            }
        }
        assertThat(person.skills.skills).contains(Soft("Solve Problem"))
    }

    @Test
    fun hard() {
        val person = introduce {
            name("김세현")
            company("inoon")
            skills {
                soft("Solve Problem")
                hard("Kotlin")
            }
        }
        assertThat(person.skills.skills).contains(Hard("Kotlin"))
    }

    @Test
    fun language() {
        val person = introduce {
            name("김세현")
            company("inoon")
            skills {
                soft("Solve Problem")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages.languages).contains(Language("Korean", 5))
        assertThat(person.languages.languages).contains(Language("English", 3))
    }
}
