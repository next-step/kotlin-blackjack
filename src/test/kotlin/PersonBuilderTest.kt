import models.SkillType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PersonBuilderTest {
    @ParameterizedTest
    @ValueSource(strings = ["Aparna", "Pattathil"])
    fun `test name assignment`(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @ParameterizedTest
    @ValueSource(strings = ["DH", ""])
    fun `test company assignment`(value: String) {
        val person = introduce {
            name("Aparna")
            company(value)
        }
        assertThat(person.company).isEqualTo(value)
    }

    @Test
    fun `test skill assignment`() {
        val person = introduce {
            name("Aparna")
            company("DH")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        val softSkills = person.skills.filter { it.type == SkillType.SOFT }
        val hardSkills = person.skills.filter { it.type == SkillType.HARD }

        assertThat(person.skills.size).isEqualTo(3)
        assertTrue {
            softSkills.any { it.description == "A passion for problem solving" }
            softSkills.any { it.description == "Good communication skills" }
            hardSkills.any { it.description == "Kotlin" }
        }
        assertFalse {
            softSkills.any {
                it.description == "Kotlin"
            }
        }
    }

    @Test
    fun `test language assignment`() {
        val person = introduce {
            name("Aparna")
            company("DH")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        val korean = person.languages.firstOrNull { it.name == "Korean" }
        val english = person.languages.find { it.name == "English" }

        assertEquals(2, person.languages.size)
        assertTrue(korean?.proficiency == 5)
        assertTrue(english?.proficiency == 3)

    }

}