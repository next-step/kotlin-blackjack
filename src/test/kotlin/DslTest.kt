import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DslTest {

    @ParameterizedTest
    @CsvSource(
        "정지선1,카카오1", "정지선2,카카오2"
    )
    fun introduce(expectedName: String, expectedCompany: String) {
        val person = introduce {
            name(expectedName)
            company(expectedCompany)

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

        val expectedSkills = listOf(
            Skill("A passion for problem solving", SkillType.Soft),
            Skill("Good communication skills", SkillType.Soft),
            Skill("Kotlin", SkillType.Hard)
        )

        val expectedLanguages = listOf(
            Language("Korean", 5),
            Language("English", 3)
        )

        assertThat(person.name).isEqualTo(expectedName)
        assertThat(person.company).isEqualTo(expectedCompany)
        assertThat(person.skills).isEqualTo(expectedSkills)
        assertThat(person.languages).isEqualTo(expectedLanguages)
    }
}
