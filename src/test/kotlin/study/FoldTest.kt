package study

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class FoldTest : FreeSpec({
    "fold는 누적 값을 기반으로 합산한다" {
        val sumOfOthers = 10
        val numbers = listOf(1, 1, 1, 1, 1)

        val result =
            numbers.fold(sumOfOthers) { accumulated, number ->
                accumulated + number
            }

        result shouldBe 15
    }
})
