package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.card.Cards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameResultCalculatorTest : StringSpec({
    "딜러가 21점을 넘어가면 남아 있던 플레이어들은 점수에 관계 없이 승리한다" {
        val dealer = Dealer(
            "dealer",
            Cards(
                mutableListOf(
                    Card(CardNumber.JACK, CardSymbol.HEART),
                    Card(CardNumber.QUEEN, CardSymbol.HEART),
                    Card(CardNumber.KING, CardSymbol.HEART)
                )
            )
        )
        val playerUnderMaxScore = Player(
            "playerUnderMaxScore",
            Cards(
                mutableListOf(
                    Card(CardNumber.ACE, CardSymbol.HEART),
                    Card(CardNumber.SIX, CardSymbol.HEART),
                    Card(CardNumber.NINE, CardSymbol.HEART)
                )
            )
        )
        val playerOverMaxScore = Player(
            "playerOverMaxScore",
            Cards(
                mutableListOf(
                    Card(CardNumber.JACK, CardSymbol.HEART),
                    Card(CardNumber.QUEEN, CardSymbol.HEART),
                    Card(CardNumber.KING, CardSymbol.HEART)
                )
            )
        )
        val players = Players(listOf(playerUnderMaxScore, playerOverMaxScore))

        GameResultCalculator.setResult(dealer, players)

        players.forEach {
            it.gameResult shouldBe GameResult.WIN
        }
    }
})
