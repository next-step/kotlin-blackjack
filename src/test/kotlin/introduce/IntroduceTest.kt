package introduce

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

internal class IntroduceTest : AnnotationSpec() {

    @Test
    fun introduce() {
        val person = introduce {
            name = "시경덕"
            company = "쇼핑몰"
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

        with(person) {
            name shouldBe "시경덕"
            company shouldBe "쇼핑몰"
            skills["soft"]!! shouldContainAll listOf("A passion for problem solving", "Good communication skills")
            skills["hard"]!! shouldContainAll listOf("Kotlin")
            languages["Korean"]!! shouldBe 5
            languages["English"]!! shouldBe 3
        }
    }
}
