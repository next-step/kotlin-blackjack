import dsl.Language
import dsl.Person
import dsl.Skill
import dsl.introduce
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
introduce {
name("박재성")
company("우아한형제들")
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
 */
class DslTest {
    @ValueSource(strings = ["윤도현", "엘렌"])
    @ParameterizedTest
    fun name(value: String) {
        // val person: Person = introduce ({
        //     this.name("윤도현")
        // })
        val person: Person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun resume() {
        val person: Person = introduce {
            name("윤도현")
            company("카카오")
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

        val expectedName = "윤도현"
        val expectedCompany = "카카오"
        val expectedSkillSet = listOf(
            Skill.Soft("A passion for problem solving"),
            Skill.Soft("Good communication skills"),
            Skill.Hard("Kotlin"),
        )

        val expectedLanguageSet = listOf(
            Language("Korean", 5),
            Language("English", 3),
        )

        assertThat(person.name).isEqualTo(expectedName)
        assertThat(person.company).isEqualTo(expectedCompany)
        assertThat(person.skills).isEqualTo(expectedSkillSet)
        assertThat(person.languages).isEqualTo(expectedLanguageSet)
    }
}
