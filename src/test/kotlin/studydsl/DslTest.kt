import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import studydsl.HardSkill
import studydsl.Language
import studydsl.SoftSkill
import studydsl.introduce

class DslTest {
    @ValueSource(strings = ["김창민", "PABLO"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person =
            introduce {
                name(value)
            }
        person.name shouldBe value
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("김창민")
                company("엔비디아")
            }
        person.name shouldBe "김창민"
        person.company shouldBe "엔비디아"
    }

    @Test
    fun softSkill() {
        val person =
            introduce {
                name("김창민")
                company("엔비디아")
                skills {
                    soft("A passion for problem solving")
                }
            }
        person.skills.skills.size shouldBe 1
        person.skills.skills[0] shouldBe SoftSkill(name = "A passion for problem solving")
    }

    @Test
    fun hardSkill() {
        val person =
            introduce {
                name("김창민")
                company("엔비디아")
                skills {
                    hard("Node.js")
                }
            }
        person.skills.skills.size shouldBe 1
        person.skills.skills[0] shouldBe HardSkill(name = "Node.js")
    }

    @Test
    fun totalSkills() {
        val person =
            introduce {
                name("김창민")
                company("엔비디아")
                skills {
                    soft("A passion for problem solving")
                    hard("Node.js")
                    hard("Kotlin")
                }
            }
        person.skills.skills.size shouldBe 3
        person.skills.skills[0] shouldBe SoftSkill(name = "A passion for problem solving")
        person.skills.skills[1] shouldBe HardSkill(name = "Node.js")
        person.skills.skills[2] shouldBe HardSkill(name = "Kotlin")
    }

    @Test
    fun languages() {
        val person =
            introduce {
                name("김창민")
                company("엔비디아")
                skills {
                    soft("A passion for problem solving")
                    hard("Node.js")
                    hard("Kotlin")
                }
                languages {
                    "Koren" level 5
                    "English" level 2
                }
            }

        person.languages.languages[0] shouldBe Language(name = "Koren", level = 5)
        person.languages.languages[1] shouldBe Language(name = "English", level = 2)
    }
}
