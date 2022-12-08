package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CalculatorTest : StringSpec({

    "생성된 카드의 합" {
        val inputValues = listOf(CardNumber.Default(3), CardNumber.Default(8), CardNumber.Default(10))
        val cardCalculator = CardCalculator(
            inputValues.map { Card(Card.CARD_SHAPE.Clover, it) }
        )

        cardCalculator.sum() shouldBe inputValues.sumOf { it.value }
    }

    "Ace는 1로 계산할 수 있다" {
        val inputValues = listOf(CardNumber.Ace(), CardNumber.Default(10), CardNumber.Default(10))
        val cardCalculator = CardCalculator(
            inputValues.map { Card(Card.CARD_SHAPE.Clover, it) }
        )

        cardCalculator.sum() shouldBe inputValues.sumOf { it.value }
    }

    "Ace는 11로 계산할 수 있다" {
        val inputValues = listOf(CardNumber.Ace(), CardNumber.Default(2), CardNumber.Default(3))
        val cardCalculator = CardCalculator(
            inputValues.map { Card(Card.CARD_SHAPE.Clover, it) }
        )

        cardCalculator.sum() shouldBe inputValues.sumOf { it.value } + 10
    }

    "Jack, Queen, King 은 각각 10으로 계산한다" {
        val inputValues = listOf(CardNumber.Default(3), CardNumber.Default(2), CardNumber.Jack())
        val cardCalculator = CardCalculator(
            inputValues.map { Card(Card.CARD_SHAPE.Clover, it) }
        )

        cardCalculator.sum() shouldBe inputValues.sumOf { it.value }
    }
})
