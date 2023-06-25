package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *  name("홍길동")
 *  company("활빈당)
 *  skills {
 *      soft("A passion for problem solving")
 *      soft("Good communication skills")
 *      hard("Kotlin")
 *    }
 *  languages {
 *      "Korean" level 5
 *      "English" level 3
 *    }
 *  }
 */
class DslTest {

    @ValueSource(strings = ["홍길동", "제이슨"])
    @ParameterizedTest
    internal fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @Test
    internal fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @Test
    internal fun skills() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.softSkills shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.hardSkills shouldBe listOf("Kotlin")

    }

    @Test
    internal fun languages() {
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
        person.softSkills shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.hardSkills shouldBe listOf("Kotlin")
        person.languageSkills shouldBe listOf(Pair("Korean", 5), Pair("English", 3))

    }

    private fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }
}
