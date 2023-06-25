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

class BlackJackStrategyTest : StringSpec({
    "딜러가 블랙잭 일 때" {
        forAll(
            row(
                cards(
                    Card(CardNumber.SEVEN, CardSymbol.SPADE),
                    Card(CardNumber.TEN, CardSymbol.SPADE),
                ),
                MatchState.LOSE
            ),
            row(
                cards(
                    Card(CardNumber.ACE, CardSymbol.SPADE),
                    Card(CardNumber.TEN, CardSymbol.CLUB),
                ),
                MatchState.PUSH
            ),
        ) { cards, matchState ->
            val player = GamePlayer("test", cards)

            val dealer = Dealer(
                cards(
                    Card(CardNumber.ACE, CardSymbol.DIAMOND),
                    Card(CardNumber.JACK, CardSymbol.CLUB),
                )
            )

            val result = BlackJackStrategy.match(dealer, player)
            result shouldBe matchState
        }
    }
})
