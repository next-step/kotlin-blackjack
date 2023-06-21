package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 * name("박세준")
 * company("에스이랩")
 * skills {
 * soft("A passion for problem solving")
 * soft("Good communication skills")
 * hard("Kotlin")
 * }
 * languages {
 * "Korean" level 5
 * "Japanese" level 3
 * "English" level 1
 * }
 * }
 */
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

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }
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

    fun build(): Person {
        return Person(name, company)
    }
}
