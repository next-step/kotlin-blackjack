package dsl

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["김보라", "보라돌"])
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
            name("김보라")
            company("우아한시스터즈")
        }
        person.name shouldBe "김보라"
        person.company shouldBe "우아한시스터즈"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("김보라")
            company("우아한시스터즈")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "김보라"
        person.company shouldBe "우아한시스터즈"

        val skills = person.skills.values
        skills.size shouldBe 3
        skills[0].description shouldBe "A passion for problem solving"
        skills[1].description shouldBe "Good communication skills"
        skills[2].description shouldBe "Kotlin"

        val languages = person.languages.values
        languages.size shouldBe 0
    }

    @Test
    fun languages() {
        val person = introduce {
            name("김보라")
            company("우아한시스터즈")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        person.name shouldBe "김보라"
        person.company shouldBe "우아한시스터즈"

        val skills = person.skills.values
        skills.size shouldBe 0

        val languages = person.languages.values
        languages.size shouldBe 2
        languages[0].lang shouldBe "Korean"
        languages[0].level shouldBe 5
        languages[1].lang shouldBe "English"
        languages[1].level shouldBe 3
    }

    @Test
    fun person() {
        val person = introduce {
            name("김보라")
            company("우아한시스터즈")
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

        person.name shouldBe "김보라"
        person.company shouldBe "우아한시스터즈"

        val skills = person.skills.values
        skills.size shouldBe 3
        skills[0].description shouldBe "A passion for problem solving"
        skills[1].description shouldBe "Good communication skills"
        skills[2].description shouldBe "Kotlin"

        val languages = person.languages.values
        languages.size shouldBe 2
        languages[0].lang shouldBe "Korean"
        languages[0].level shouldBe 5
        languages[1].lang shouldBe "English"
        languages[1].level shouldBe 3
    }
}
