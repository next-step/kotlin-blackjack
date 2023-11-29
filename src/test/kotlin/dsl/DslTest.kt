package dsl

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun introduce() {
        val person = introduce {
            name("조경현")
            company("카이쵸컴퍼니")
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

        person.name shouldBe "조경현"
        person.company shouldBe "카이쵸컴퍼니"
        person.skills?.soft shouldContainExactly listOf("A passion for problem solving", "Good communication skills")
        person.skills?.hard shouldContainExactly listOf("Kotlin")
        person.languages?.languages?.shouldContain("Korean" to 5)
        person.languages?.languages?.shouldContain("English" to 3)
    }
}
