package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class LanguagesTest : StringSpec({
    "언어 이름과 레벨을 받아 언어를 반환한다" {
        val languages = introduceLanguages {
            "Korean" level 5
            "English" level 3
        }

        languages shouldBe Languages(
            listOf(
                Language("Korean", 5),
                Language("English", 3),
            )
        )
    }
})
