package dsltest.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["홍길동", "최환"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company shouldBe null
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("하하")
                soft("쉬움")
                hard("어려움")
            }
            languages {
                "korean" level 3
            }
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills[0].description shouldBe "하하"
        person.skills[1].description shouldBe "쉬움"
        person.skills[2].description shouldBe "어려움"
        person.languages[0].name shouldBe "korean"
        person.languages[0].level shouldBe 3
    }
}