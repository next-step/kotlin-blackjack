package blackjack.domain.result.match

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.cards
import blackjack.domain.player.Dealer
import blackjack.domain.player.GamePlayer
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class NormalMatchStrategyTest : StringSpec({
    "딜러가 보통 일 때" {
        forAll(
            row(
                cards(
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                    Card(CardNumber.TEN, CardSymbol.CLUB),
                    Card(CardNumber.TEN, CardSymbol.HEART),
                ),
                MatchState.LOSE
            ),
            row(
                cards(
                    Card(CardNumber.SEVEN, CardSymbol.SPADE),
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                ),
                MatchState.LOSE
            ),
            row(
                cards(
                    Card(CardNumber.EIGHT, CardSymbol.SPADE),
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                ),
                MatchState.PUSH
            ),
            row(
                cards(
                    Card(CardNumber.NINE, CardSymbol.SPADE),
                    Card(CardNumber.TEN, CardSymbol.CLUB),
                ),
                MatchState.WIN
            ),
        ) { cards, matchState ->
            val player = GamePlayer("test", cards)

            val dealer = Dealer(
                cards(
                    Card(CardNumber.TEN, CardSymbol.DIAMOND),
                    Card(CardNumber.EIGHT, CardSymbol.CLUB),
                )
            )

            val result = NormalMatchStrategy.match(dealer, player)
            result shouldBe matchState
        }
    }
})
