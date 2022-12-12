package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun introduce() {
        val person: Person = introduce {
            name("손진영")
            company("네카라쿠배")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "손진영"
        person.company shouldBe "네카라쿠배"
        person.skills?.soft shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skills?.hard shouldBe listOf("Kotlin")
    }
}
