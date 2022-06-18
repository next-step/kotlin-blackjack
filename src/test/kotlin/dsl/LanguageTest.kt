package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class LanguageTest : StringSpec({

    "Language는 언어의 이름과 숙련도를 가진다." {
        val language = language {
            "Hangul" level 9999
            "English" level 5
        }

        language[0].name shouldBe "Hangul"
        language[0].level shouldBe 9999
        language[1].name shouldBe "English"
        language[1].level shouldBe 5
    }
})
