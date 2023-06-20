package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

// introduce {
//    name("홍길동")
//    company("활빈당")
//    skills {
//        soft("A passion for problem solving")
//        soft("Good communication skills")
//        hard("Kotlin")
//    }
//    languages {
//        "Korean" level 5
//        "English" level 3
//    }
// }
class DslTest {
    @ValueSource(strings = ["홍길동", "유재인"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            this.name(value)
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

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"

        val skills = person.skills.skills
        skills.size shouldBe 3
        skills[0].description shouldBe "A passion for problem solving"
        skills[1].description shouldBe "Good communication skills"
        skills[2].description shouldBe "Kotlin"
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

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"

        val skills = person.skills.skills
        skills.size shouldBe 3
        skills[0].description shouldBe "A passion for problem solving"
        skills[1].description shouldBe "Good communication skills"
        skills[2].description shouldBe "Kotlin"

        val languages = person.languages.languages
        languages.size shouldBe 2
        languages[0].country shouldBe "Korean"
        languages[0].level shouldBe 5
        languages[1].country shouldBe "English"
        languages[1].level shouldBe 3
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
