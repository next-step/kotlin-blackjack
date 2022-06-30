package blackjack.application

import blackjack.domain.AceDifferScoreCalculateStrategy
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.GameResult
import blackjack.view.GameResults
import blackjack.view.response.CardResponse

private const val FIRST_DRAW_COUNT = 2

object BlackJackService {

    fun createPlayers(names: List<String>): List<Player> {
        require(names.size in Player.MIN_COUNT..Player.MAX_COUNT) { "플레이어 수는 1 이상 26 이하여야 합니다." }

        return names.map { Player(it) }
    }

    fun calculate(players: List<Player>): GameResults {
        val gameResults = players.map {
            GameResult(
                it.name,
                it.hand.cards.map { card -> CardResponse.from(card) },
                AceDifferScoreCalculateStrategy.calculate(it.hand.cards).score
            )
        }
        return GameResults(gameResults)
    }

    fun drawFirst(players: List<Player>, deck: Deck) {
        players.forEach { draw(it, deck, FIRST_DRAW_COUNT) }
    }

    fun draw(player: Player, deck: Deck, drawCount: Int) {
        repeat(drawCount) {
            player.addCard(deck.draw())
        }
    }
}
