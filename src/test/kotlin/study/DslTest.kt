package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun introduceTest() {
        val person: Person = introduce {
            name("이승현")
            company("회사명")
        }
        assertThat(person.company).isEqualTo("회사명")
    }

    @Test
    fun companyTest() {
        val person: Person = introduce {
            name("이승현")
            company("회사명")
        }

        assertThat(person.name).isEqualTo("이승현")
        assertThat(person.company).isEqualTo("회사명")
    }

    @Test
    fun skillsTest() {
        val person: Person = introduce {
            name("이승현")
            company("회사명")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.skills).isEqualTo(
            Skills(
                listOf(
                    SoftSkill("A passion for problem solving"),
                    SoftSkill("Good communication skills"),
                    HardSkill("Kotlin")
                )
            )
        )
    }

    @Test
    fun languageTest() {
        val person: Person = introduce {
            name("이승현")
            company("회사명")
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

        assertThat(person.languages).isEqualTo(
            Languages(
                listOf(
                    Language("Korean", 5),
                    Language("English", 3)
                )
            )
        )
    }
}










