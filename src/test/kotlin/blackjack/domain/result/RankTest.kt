package blackjack.domain.result

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Number
import blackjack.domain.card.Suit
import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Player
import blackjack.domain.participantion.Price
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue

class RankTest : StringSpec({

    "참여자 이름으로 우승 여부를 확인하다." {
        val price = Price(0)

        val winner = Player(
            "winner",
            Cards(
                listOf(
                    Card(Suit.CLUB, Number.TEN),
                    Card(Suit.CLUB, Number.NINE)
                )
            ),
            price
        )

        val looser = Player(
            "looser",
            Cards(
                listOf(
                    Card(Suit.CLUB, Number.TEN),
                    Card(Suit.CLUB, Number.SEVEN)
                )
            ),
            price
        )

        val dealer = Dealer(
            "dealer",
            Cards(
                listOf(
                    Card(Suit.CLUB, Number.TEN),
                    Card(Suit.CLUB, Number.EIGHT)
                )
            )
        )

        val winners = Winners.from(dealer, listOf(winner, looser))
        winners.exist(winner.name).shouldBeTrue()
    }
})
