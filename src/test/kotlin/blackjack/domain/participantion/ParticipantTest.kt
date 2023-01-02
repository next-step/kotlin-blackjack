package blackjack.domain.participantion

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Number
import blackjack.domain.card.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.booleans.shouldBeTrue

class ParticipantTest : StringSpec({
    "블랙잭이거나 다른 참가자 보다 점수가 높으면 이김을 알 수 있다." {
        val price = Price(0)

        val looser = Player(
            "loser",
            Cards(
                listOf(
                    Card(Suit.CLUB, Number.TWO),
                    Card(Suit.HEART, Number.TWO)
                )
            ),
            price
        )

        forAll(
            row(
                Cards(
                    listOf(
                        Card(Suit.CLUB, Number.TEN),
                        Card(Suit.CLUB, Number.ACE)
                    )
                )
            ),
            row(
                Cards(
                    listOf(
                        Card(Suit.CLUB, Number.TEN),
                        Card(Suit.CLUB, Number.NINE)
                    )
                )
            )
        ) { cards ->
            val winner = Player(
                "winner",
                cards,
                price
            )

            val isWin = winner.isWin(looser)
            isWin.shouldBeTrue()
        }
    }

    "버스트이거나 다른 참가자 보다 점수가 낮으면 졌음을 알 수 있다." {
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

        forAll(
            row(
                Cards(
                    listOf(
                        Card(Suit.CLUB, Number.TWO),
                        Card(Suit.HEART, Number.TWO)
                    )
                )
            ),
            row(
                Cards(
                    listOf(
                        Card(Suit.CLUB, Number.TEN),
                        Card(Suit.CLUB, Number.NINE),
                        Card(Suit.CLUB, Number.EIGHT)
                    )
                )
            )
        ) { cards ->
            val loser = Player(
                "loser",
                cards,
                price
            )

            val isLose = loser.isLose(winner)
            isLose.shouldBeTrue()
        }
    }
})
