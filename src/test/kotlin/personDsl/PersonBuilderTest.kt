package personDsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PersonBuilderTest {
    @ParameterizedTest
    @ValueSource(strings = ["Karyna", "Name"])
    fun `when name is valid should assign name`(name: String) {
        val person: Person =
            introduce {
                name(name)
            }
        person.name shouldBe name
    }

    @ParameterizedTest
    @ValueSource(strings = ["DH"])
    fun `when company is valid should assign company`(company: String) {
        val person: Person =
            introduce {
                name("Karyna")
                company(company)
            }
        person.company shouldBe company
    }

    @Test
    fun `when a skill is valid should add it to the person`() {
        val softSkill = "Good communication skills"
        val hardSkill = "Kotlin"
        val person: Person =
            introduce {
                name("Karyna")
                company("DH")
                skills {
                    soft(softSkill)
                    hard(hardSkill)
                }
            }
        person.softSkills shouldBe listOf(softSkill)
        person.hardSkills shouldBe listOf(hardSkill)
    }

    @Test
    fun `when multiple skills are added should add all to the person`() {
        val softSkills = listOf("Good communication skills", "Charisma")
        val hardSkills = listOf("Kotlin", "SQL")
        val person: Person =
            introduce {
                name("Karyna")
                company("DH")
                skills {
                    softSkills.forEach {
                        soft(it)
                    }
                    hardSkills.forEach { hard(it) }
                }
            }
        person.softSkills shouldBe softSkills
        person.hardSkills shouldBe hardSkills
    }

    @Test
    fun `when language is valid should add it to the person`() {
        val language = LanguageLevel("English", 4)
        val person: Person =
            introduce {
                name("Karyna")
                company("DH")
                languages {
                    "English" to 4
                }
            }
        person.languages shouldBe listOf(language)
    }

    @Test
    fun `when multiple languages are added should add all to the person`() {
        val languages = listOf(LanguageLevel("English", 4), LanguageLevel("German", 2))
        val person: Person =
            introduce {
                name("Karyna")
                company("DH")
                languages {
                    languages.forEach {
                        it.language to it.level
                    }
                }
            }
        person.languages shouldBe languages
    }
}
