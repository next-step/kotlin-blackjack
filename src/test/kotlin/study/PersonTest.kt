package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun `이름 설정`() {
        val person = introduce {
            name("jiwhun")
        }

        assertThat(person.name).isEqualTo("jiwhun")
    }

    @Test
    fun `회사 설정`() {
        val person = introduce {
            company("woowa")
        }

        assertThat(person.company).isEqualTo("woowa")
    }

    @Test
    fun `스킬 설정`() {
        val person = introduce {
            name("jiwhun")
            company("woowa")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.skills.size).isEqualTo(3)
        assertThat(person.skills.contains(Skill("Kotlin"))).isEqualTo(true)
    }

    @Test
    fun `언어 설정`() {
        val person = introduce {
            name("jiwhun")
            company("woowa")
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

        assertThat(person.languages.size).isEqualTo(2)
        assertThat(person.languages.containsKey("English")).isEqualTo(true)
    }
}
