import io.kotest.matchers.shouldBe
import model.Language
import model.Skill
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *     name("신진혁")
 *     company("맵시")
 *     skills {
 *         soft("A passion for problem solving")
 *         soft("Good communication skills")
 *         hard("Kotlin")
 *     }
 *     languages {
 *         "Korean" level 5
 *         "English" level 3
 *     }
 * }
 */
class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "홍"])
    fun nameTest(name: String) {
        val person =
            introduce {
                name(name)
            }
        person.name shouldBe name
    }

    @Test
    fun companyTest() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
            }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @Test
    fun skillsTest() {
        val person =
            introduce {
                name("ㅇㅇ")
                company("dd")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }

        person.skills?.skills shouldBe
            listOf(
                Skill(Skill.Type.SOFT, "A passion for problem solving"),
                Skill(Skill.Type.SOFT, "Good communication skills"),
                Skill(Skill.Type.HARD, "Kotlin"),
            )
    }

    @Test
    fun languagesTest() {
        val person =
            introduce {
                name("ㅇㅇ")
                company("dd")
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

        person.languages?.languages shouldBe
            listOf(
                Language(Language.Type.KOREAN, 5),
                Language(Language.Type.ENGLISH, 3),
            )
    }
}
