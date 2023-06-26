package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
introduce {
    name("박상준")
    company("준준컴패니")
    skills {
        soft("A passion for problem solving")
        hard("Kotlin")
    }
    languages {
        "Korean" level 5
        "English" level 3
    }
}
*/
class DslTest {

    @Test
    fun `name`() {
        val person: Person = introduce {
            name("박상준")
        }
        person.name shouldBe "박상준"
    }

    @Test
    fun `company`() {
        val person: Person = introduce {
            company("준준컴패니")
        }
        person.company shouldBe "준준컴패니"
    }

    @Test
    fun `skills`() {
        val person: Person = introduce {
            skills {
                soft("A passion for problem solving")
                hard("Kotlin")
            }
        }
        person.skills[0] shouldBe Soft("A passion for problem solving")
        person.skills[1] shouldBe Hard("Kotlin")
    }

    @Test
    fun `language`() {
        val person: Person = introduce {
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.languages[0] shouldBe Language("Korean", 5)
        person.languages[1] shouldBe Language("English", 3)
    }

    @Test
    fun `introduce person`() {
        val person = introduce {
            name("박상준")
            company("준준컴패니")
            skills {
                soft("A passion for problem solving")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        person.name shouldBe "박상준"
        person.company shouldBe "준준컴패니"
        person.skills[0] shouldBe Soft("A passion for problem solving")
        person.skills[1] shouldBe Hard("Kotlin")
        person.languages[0] shouldBe Language("Korean", 5)
        person.languages[1] shouldBe Language("English", 3)
    }
}
