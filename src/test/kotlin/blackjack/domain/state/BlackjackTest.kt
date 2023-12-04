package blackjack.domain.state

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Cards
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackjackTest : StringSpec({

    "첫 턴이 진행되자마자 블랙잭 상태가 되면 카드를 받을 수 없다." {
        val blackjack = Blackjack(cards = Cards())

        shouldThrow<IllegalStateException> {
            val card = Card(number = CardNumber.TWO, pattern = CardPattern.HEART)
            blackjack.receiveCard(card)
        }
    }

    "점수를 비교하여 게임 결과를 반환한다. - 무승부" {
        val blackjack = createBlackjack()
        val otherScore = Score(21)

        val result = blackjack.calculateResult(otherScore)

        result shouldBe GameResult.Result.DRAW
    }

    "점수를 비교하여 게임 결과를 반환한다. - 승리" {
        val blackjack = createBlackjack()
        val otherScore = Score(19)

        val result = blackjack.calculateResult(otherScore)

        result shouldBe GameResult.Result.WIN
    }
})

private fun createBlackjack(): Blackjack {
    val blackjack = Blackjack(
        cards = Cards().apply {
            add(Card(number = CardNumber.ACE, pattern = CardPattern.HEART))
            add(Card(number = CardNumber.TEN, pattern = CardPattern.HEART))
        }
    )
    return blackjack
}
