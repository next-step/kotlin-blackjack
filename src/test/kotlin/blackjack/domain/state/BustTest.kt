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

class BustTest : StringSpec({
    "버스트가 되면 카드를 받을 수 없다." {
        val bust = Bust(cards = Cards())

        shouldThrow<IllegalStateException> {
            val card = Card(number = CardNumber.TWO, pattern = CardPattern.HEART)
            bust.receiveCard(card)
        }
    }

    "버스트 상태라면 게임의 결과는 실패이다." {
        val bust = Bust(
            cards = Cards().apply {
                add(Card(number = CardNumber.TEN, pattern = CardPattern.HEART))
                add(Card(number = CardNumber.TEN, pattern = CardPattern.HEART))
                add(Card(number = CardNumber.TWO, pattern = CardPattern.HEART))
            }
        )

        val result = bust.calculateResult(otherScore = Score(25))

        result shouldBe GameResult.Result.LOSE
    }
})
