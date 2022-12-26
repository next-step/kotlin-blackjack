package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class ResultStatusTest : StringSpec({
    "왼쪽 숫자가 더 크다면 WIN 이다." {
        val result = 20 match 19
        result shouldBe ResultStatus.WIN
    }

    "왼쪽 숫자가 더 작다면 LOSE 다." {
        val result = 20 match 21
        result shouldBe ResultStatus.LOSE
    }

    "비교숫자가 똑같다면 DRAW 다." {
        val result = 20 match 20
        result shouldBe ResultStatus.DRAW
    }
})
