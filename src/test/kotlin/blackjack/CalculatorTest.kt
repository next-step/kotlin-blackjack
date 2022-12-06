package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CalculatorTest : StringSpec({

    "생성된 카드의 합" {
        val inputValues = listOf(CardNumber(3), CardNumber(11), CardNumber(10))
        val cardCalculator = CardCalculator(
            inputValues.map { Card(CARD_SHAPE.Clover, it) }
        )

        cardCalculator.sum() shouldBe inputValues.sumOf { it.value }
    }

    "Ace는 1로 계산할 수 있다" {
        val inputValues = listOf(CardNumber(1), CardNumber(11), CardNumber(10))
        val cardCalculator = CardCalculator(
            inputValues.map { Card(CARD_SHAPE.Clover, it) }
        )

        cardCalculator.sum() shouldBe inputValues.sumOf { it.value }
    }

    "Ace는 11로 계산할 수 있다" {
        val inputValues = listOf(CardNumber(1), CardNumber(2), CardNumber(3))
        val cardCalculator = CardCalculator(
            inputValues.map { Card(CARD_SHAPE.Clover, it) }
        )

        cardCalculator.sum() shouldBe inputValues.sumOf { it.value } + 10
    }
})
