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
    fun `when soft skill is valid should add it to the person`() {
        val softSkill = "Good communication skills"
        val person: Person =
            introduce {
                name("Karyna")
                company("DH")
                skills {
                    soft(softSkill)
                }
            }
        person.softSkills shouldBe listOf(softSkill)
    }

    @Test
    fun `when multiple soft skills are added should add all to the person`() {
        val softSkills = listOf("Good communication skills", "Charisma")
        val person: Person =
            introduce {
                name("Karyna")
                company("DH")
                skills {
                    softSkills.forEach {
                        soft(it)
                    }
                }
            }
        person.softSkills shouldBe softSkills
    }
}
