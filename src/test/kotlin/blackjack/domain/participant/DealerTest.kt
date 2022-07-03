package blackjack.domain.participant

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardNumber
import blackjack.domain.deck.CardPattern
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class DealerTest : FreeSpec({

    "딜러는 17점이 넘었는지 아닌지를 알 수 있다." - {
        val sixScoreCard = Card(CardPattern.CLOVER, CardNumber.SIX)
        val sevenScoreCard = Card(CardPattern.CLOVER, CardNumber.SEVEN)
        val tenScoreCard = Card(CardPattern.CLOVER, CardNumber.TEN)

        "17점을 넘지 않았다." {
            val dealer = Dealer()
            dealer.receiveInitCards(
                firstCard = sixScoreCard,
                secondCard = tenScoreCard
            )

            dealer.isOverThenLimitScore() shouldBe false
        }

        "17점을 넘었다." {
            val dealer = Dealer()
            dealer.receiveInitCards(
                firstCard = sevenScoreCard,
                secondCard = tenScoreCard
            )

            dealer.isOverThenLimitScore() shouldBe true
        }
    }
})
