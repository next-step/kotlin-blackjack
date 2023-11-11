package study

import io.kotest.matchers.shouldBe
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

    @ValueSource(strings = ["홍길동", "허영운"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @Test
    fun skills() {
        val person = introduce {
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.skills?.contains(Skill("soft", "A passion for problem solving")) shouldBe true
        person.skills?.contains(Skill("soft", "Good communication skills")) shouldBe true
        person.skills?.contains(Skill("hard", "Kotlin")) shouldBe true
    }

    @Test
    fun languages() {
        val person = introduce {
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        person.languages?.languages?.get("Korean") shouldBe 5
        person.languages?.languages?.get("English") shouldBe 3
    }

    @Test
    fun all() {
        val person = introduce {
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

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"

        person.skills?.contains(Skill("soft", "A passion for problem solving")) shouldBe true
        person.skills?.contains(Skill("soft", "Good communication skills")) shouldBe true
        person.skills?.contains(Skill("hard", "Kotlin")) shouldBe true

        person.languages?.languages?.get("Korean") shouldBe 5
        person.languages?.languages?.get("English") shouldBe 3
    }
}
