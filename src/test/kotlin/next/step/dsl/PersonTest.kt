package next.step.dsl

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PersonTest {

    @ValueSource(strings = ["홍길동", "이동준"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }

        person.name shouldBe value
        person.company.shouldBeNull()
        person.skills shouldBe Skills()
        person.languages shouldBe Languages()
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills shouldBe Skills()
        person.languages shouldBe Languages()
    }

    @Test
    fun languages() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.languages.get("Korean") shouldBe 5
        person.languages.get("English") shouldBe 3
        person.skills shouldBe Skills()
    }

    @Test
    fun skills() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            languages {
                "Korean" level 5
                "English" level 3
            }
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.languages.get("Korean") shouldBe 5
        person.languages.get("English") shouldBe 3
        person.skills.soft shouldBe setOf("A passion for problem solving", "Good communication skills")
        person.skills.hard shouldBe setOf("Kotlin")
    }
}
