package study

import dsl.Person
import dsl.introduce
import dsl.language.Language
import dsl.skill.Hard
import dsl.skill.Soft
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun nameAndCompany() {
        val person: Person = introduce {
            name("이광재")
            company("SK플래닛")
        }

        assertAll(
            "person",
            { assertEquals("이광재", person.name) },
            { assertEquals("SK플래닛", person.company) }
        )
    }

    @Test
    fun nameAndCompanySkills() {
        val person: Person = introduce {
            name("이광재")
            company("SK플래닛")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        Assertions.assertThat(person.name).isEqualTo("이광재")
        Assertions.assertThat(person.company).isEqualTo("SK플래닛")
        Assertions.assertThat(person.skills.soft.size).isEqualTo(2)
        Assertions.assertThat(person.skills.hard.size).isEqualTo(1)
        Assertions.assertThat(person.skills.hard[0]).isEqualTo(Hard("Kotlin"))
        Assertions.assertThat(person.skills.soft[0]).isEqualTo(Soft("A passion for problem solving"))
        Assertions.assertThat(person.skills.soft[1]).isEqualTo(Soft("Good communication skills"))
    }

    @Test
    fun nameAndCompanySkillsAndLanguages() {
        val person: Person = introduce {
            name("이광재")
            company("SK플래닛")
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
        Assertions.assertThat(person.name).isEqualTo("이광재")
        Assertions.assertThat(person.company).isEqualTo("SK플래닛")
        Assertions.assertThat(person.skills.soft.size).isEqualTo(2)
        Assertions.assertThat(person.skills.hard.size).isEqualTo(1)
        Assertions.assertThat(person.skills.hard[0]).isEqualTo(Hard("Kotlin"))
        Assertions.assertThat(person.skills.soft[0]).isEqualTo(Soft("A passion for problem solving"))
        Assertions.assertThat(person.skills.soft[1]).isEqualTo(Soft("Good communication skills"))
        Assertions.assertThat(person.skills.hard).containsExactly(Hard("Kotlin"))
        Assertions.assertThat(person.skills.soft).containsExactly(Soft("A passion for problem solving"), Soft("Good communication skills"))
        Assertions.assertThat(person.languages.explain.size).isEqualTo(2)
        Assertions.assertThat(person.languages.explain).containsExactly(Language("Korean", 5), Language("English", 3))
    }
}
