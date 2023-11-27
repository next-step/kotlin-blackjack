package study

import dsl.introduce
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

    @ValueSource(strings = ["홍길동", "이준현"])
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
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.skill.getSoftSkillSize() shouldBe 2
        person.skill.getHardSkillSize() shouldBe 1
    }

    @Test
    fun languages() {
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

        person.languages.getLanguageLevel("Korean") shouldBe 5
        person.languages.getLanguageLevel("English") shouldBe 3
        person.languages.getLanguageLevel("Japanese") shouldBe null
    }

    @Test
    fun pair() {
        val pair1 = Pair(1, "one")
        val pair2 = 1.to("ont")
        val pair3 = 1 to "ont" // 인자를 하나만 받는 상황이면 . 과 소괄호 생략 - 중위표현식

        val pair4 = 1 of "one"
    }
}

// 중위 표기 함수는 infix 키워드 사용
infix fun Int.of(s: String): Pair<Int, String> = Pair(this, s)
