package blackjack.domain.score

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.cards
import blackjack.domain.player.Player
import blackjack.domain.players
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class GameResultManagerTest : StringSpec({
    "GameResults 를 얻는다" {
        val cards1 = cards(
            Card(CardNumber.ACE, CardSymbol.SPADE),
            Card(CardNumber.NINE, CardSymbol.SPADE),
            Card(CardNumber.TEN, CardSymbol.SPADE),
        )
        val player1 = Player("test", cards1)

        val cards2 = cards(
            Card(CardNumber.ACE, CardSymbol.HEART),
            Card(CardNumber.NINE, CardSymbol.HEART),
            Card(CardNumber.TEN, CardSymbol.HEART),
        )
        val player2 = Player("test", cards2)

        val players = players(player1, player2)
        val results = GameResultManager.getGameResults(players)

        results shouldContainExactlyInAnyOrder listOf(
            GameResult(player1, Score(20)),
            GameResult(player2, Score(20)),
        )
    }
})
