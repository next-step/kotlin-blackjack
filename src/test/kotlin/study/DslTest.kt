package study

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["홍길동", "박세준"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @ValueSource(strings = ["활빈당", "에스이랩"])
    @ParameterizedTest
    fun company(value: String) {
        val person = introduce {
            name("홍길동")
            company(value)
        }
        person.name shouldBe "홍길동"
        person.company shouldBe value
    }

    @Test
    fun skill() {
        val person = introduce {
            name("홍길동")
            skill {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "홍길동"
        person.company.shouldBeNull()
        person.skill.shouldNotBeNull()
        person.skill.soft shouldContainAll listOf("A passion for problem solving", "Good communication skills")
        person.skill.hard shouldContain "Kotlin"
    }

    @Test
    fun language() {
        val person = introduce {
            name("홍길동")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.name shouldBe "홍길동"
        person.company.shouldBeNull()
        person.skill.shouldBeNull()
        person.language.shouldNotBeNull()
        person.language["Korean"] shouldBe 5
        person.language["English"] shouldBe 3

    }
}
