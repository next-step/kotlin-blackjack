package dsl

import dsl.languages.Language
import dsl.skills.Hard
import dsl.skills.Soft
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person = introduce {
            name("이소현")
        }

        assertThat(person.name).isEqualTo("이소현")
    }

    @Test
    fun company() {
        val person = introduce {
            name("이소현")
            company("여기어때")
        }

        assertThat(person.company).isEqualTo("여기어때")
    }

    @Test
    fun soft_skills() {
        val person = introduce {
            name("이소현")
            company("여기어때")
            skills {
                soft("A passion for problem solving")
            }
        }

        assertThat(person.skills.skills).contains(Soft("A passion for problem solving"))
    }

    @Test
    fun hard_skills() {
        val person = introduce {
            name("이소현")
            company("여기어때")
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
            name("이소현")
            company("여기어때")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 2
            }
        }

        assertThat(person.language).contains(Language("Korean", 5))
    }
}
