package study

import dsl.Language
import dsl.Person
import dsl.introduce
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val person: Person = introduce {
            name("윤영빈")
        }
        person.name shouldBe "윤영빈"
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("윤영빈")
            company("KSD")
        }
        person.name shouldBe "윤영빈"
        person.company shouldBe "KSD"
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            skills {
                name("윤영빈")
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.skills?.hardSkills?.hardSkills shouldBe listOf("Kotlin")
        person.skills?.softSkills?.softSkills shouldBe listOf("A passion for problem solving", "Good communication skills")
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            skills {
                name("윤영빈")
                languages {
                    "Korean" level 3
                    "English" level 1
                }
            }
        }
        person.languages?.languages shouldBe listOf(
            Language("Korean", 3),
            Language("English", 1)
        )
    }
}
