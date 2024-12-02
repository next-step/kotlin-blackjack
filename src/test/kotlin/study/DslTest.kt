package study

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
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
 *
 */
class DslTest {
    @DisplayName("name(name: String)을 통해 Person 객체에 name을 입력할 수 있다")
    @ValueSource(strings = ["홍길동", "양영근"])
    @ParameterizedTest
    fun name(name: String) {
        val person =
            introduce {
                name(name)
            }
        person.name shouldBe name
    }

    @DisplayName("company에 활빈당을 넣으면 해당 Person 객체의 company 필드에 활빈당을 입력하게 된다")
    @Test
    fun company() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
            }
        person.company shouldBe "활빈당"
    }

    @DisplayName("languages 내에 language 이름과 level을 넣어 person.languages에 language를 추가할 수 있다")
    @Test
    fun languages() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }

        person.languages shouldHaveSize 2
        person.languages shouldContainExactlyInAnyOrder listOf(Language("Korean", 5), Language("English", 3))
    }

    @DisplayName("introduce 내에 skills 내에 soft 와 hard를 이용해 person의 SOFT 스킬과 HARD 스킬을 추가할 수 있다")
    @Test
    fun skills() {
        val person =
            introduce {
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

        person.skills shouldHaveSize 3
        person.skills shouldContainExactlyInAnyOrder
            listOf(
                Skill(SkillType.SOFT, "A passion for problem solving"),
                Skill(SkillType.SOFT, "Good communication skills"),
                Skill(SkillType.HARD, "Kotlin"),
            )
    }
}
