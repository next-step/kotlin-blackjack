package blackjack.domain.state

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Cards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StayTest : StringSpec({
    "점수를 비교하여 게임 결과를 반환한다. - 무승부" {
        val stay = createStay()
        val otherScore = Score(12)

        val result = stay.calculateResult(otherScore)

        result shouldBe GameResult.Result.DRAW
    }

    "점수를 비교하여 게임 결과를 반환한다. - 승리" {
        val stay = createStay()
        val otherScore = Score(11)

        val result = stay.calculateResult(otherScore)

        result shouldBe GameResult.Result.WIN
    }

    "점수를 비교하여 게임 결과를 반환한다. - 패배" {
        val stay = createStay()
        val otherScore = Score(13)

        val result = stay.calculateResult(otherScore)

        result shouldBe GameResult.Result.LOSE
    }
})

private fun createStay(): Stay {
    return Stay(
        cards = Cards().apply {
            add(Card(number = CardNumber.TWO, pattern = CardPattern.HEART))
            add(Card(number = CardNumber.TEN, pattern = CardPattern.HEART))
        }
    )
}
