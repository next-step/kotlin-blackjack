package blackjack.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "이름이 빈 문자열이면 에러 발생" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            Player("")
        }
        exception.message shouldBe "플레이어 이름은 한글자 이상이여야 합니다."
    }

    "이름이 공백이면 에러 발생" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            Player(" ")
        }
        exception.message shouldBe "플레이어 이름은 한글자 이상이여야 합니다."
    }

})
