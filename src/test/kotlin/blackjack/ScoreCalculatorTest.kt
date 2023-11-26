package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ScoreCalculatorTest: StringSpec({

    "카드 점수에 맞게 최종 점수가 계산 되어야 한다" {
        ScoreCalculator.calc(listOf(Card.TWO, Card.THREE)) shouldBe 5
    }

})