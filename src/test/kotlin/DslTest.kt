import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import kotlindsl.builder.introduce
import kotlindsl.enum.SkillType
import kotlindsl.model.Language
import kotlindsl.model.Person
import kotlindsl.model.Skill
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("홍길동")
 *   company("활빈당")
 *   skills {
 *     soft("A passion for problem solving")
 *     soft("Good communication skills")
 *     hard("Kotlin")
 *   }
 *   languages {
 *     "Korean" level 5
 *     "English" level 3
 *   }
 * }
 */

class DslTest {
    @ValueSource(strings = ["홍길동", "dokdogalmaegi"])
    @ParameterizedTest
    fun name(name: String) {
        val person: Person = introduce {
            name(name)
        }

        person.name shouldBe name
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("홍길동")
            company("활빈당")
        }

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @ValueSource(strings = ["Kotlin", "Go", "Java"])
    @ParameterizedTest
    fun hard(skill: String) {
        val person: Person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                hard(skill)
            }
        }

        person.skills shouldContainExactly listOf(Skill(SkillType.Hard, skill))
    }

    @ValueSource(strings = ["A passion for problem solving", "Good communication skills", "Good voice"])
    @ParameterizedTest
    fun soft(skill: String) {
        val person: Person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft(skill)
            }
        }

        person.skills shouldContainExactly listOf(Skill(SkillType.Soft, skill))
    }

    @Test
    fun hardAndSoft() {
        val person: Person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.skills shouldContainExactly listOf(
            Skill(SkillType.Soft, "A passion for problem solving"),
            Skill(SkillType.Soft, "Good communication skills"),
            Skill(SkillType.Hard, "Kotlin"),
        )
    }

    @Test
    fun language() {
        val person: Person = introduce {
            name("홍길동")
            company("활빈당")
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

        person.language shouldContainExactly listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}
