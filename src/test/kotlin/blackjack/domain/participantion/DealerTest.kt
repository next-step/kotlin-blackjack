package blackjack.domain.participantion

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Number
import blackjack.domain.card.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class DealerTest : StringSpec({
    "딜러의 Point 는 16 이하이면 카드를 받을 수 있다." {
        val cards = Cards(
            listOf(
                Card(Suit.CLUB, Number.TEN),
                Card(Suit.CLUB, Number.SIX)
            )
        )

        val dealer = Dealer("딜러", cards)

        dealer.isHittable().shouldBeTrue()
    }

    "딜러의 Point 는 17 이상이면 카드를 받을 수 없다." {
        val cards = Cards(
            listOf(
                Card(Suit.CLUB, Number.TEN),
                Card(Suit.CLUB, Number.SEVEN)
            )
        )

        val dealer = Dealer("딜러", cards)

        dealer.isHittable().shouldBeFalse()
    }
})
