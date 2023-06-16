package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class IntroduceDslTest : StringSpec({

    "Introduce Dsl을 통해 Person을 생성할 수 있다." {
        val person = introduce {
            name("이진원")
            company("카카오스타일")
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

        person.name shouldBe "이진원"
        person.company shouldBe "카카오스타일"
        person.skill shouldBe "soft" to "A passion for problem solving"
        person.language shouldBe "Korean" to 5
    }
})
