package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 *
 *    introduce {
 *         name("김송이")
 *         company("SIA")
 *         skills {
 *             soft("kotlin")
 *             soft("good communication skills")
 *         }
 *         languages {
 *             "korean" level 5
 *             "english" level 3
 *         }
 *     }
 */
class DslTest {

    @ValueSource(strings = ["홍길동", "김송이"])
    @ParameterizedTest
    fun introduce(value: String) {
        // 가장 마지막에 오는 람다식의 경우 인자 소괄호가 아닌 소괄호 밖으로 뺄 수 있음
        // ex) require, check 와 같음
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person = introduce {
            name("김송이")
            company("SIA")
        }

        person.name shouldBe "김송이"
        person.company shouldBe "SIA"
    }

    @Test
    fun skill() {
        val person = introduce {
            name("김송이")
            company("SIA")
            skills {
                soft("kotlin")
                hard("Good communication skills")
            }
        }

        person.name shouldBe "김송이"
        person.company shouldBe "SIA"
        person.skills[0] shouldBe Soft("kotlin")
        person.skills[1] shouldBe Hard("Good communication skills")
    }

    @Test
    fun languages() {
        val person = introduce {
            name("김송이")
            company("SIA")
            skills {
                soft("kotlin")
                hard("Good communication skills")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        person.name shouldBe "김송이"
        person.company shouldBe "SIA"
        person.skills[0] shouldBe Soft("kotlin")
        person.skills[1] shouldBe Hard("Good communication skills")
        person.languages[0] shouldBe Language("Korean", 5)
        person.languages[1] shouldBe Language("English", 3)
    }
}