package blackjack.application

import blackjack.domain.AceDifferScoreCalculateStrategy
import blackjack.domain.BLACK_JACK_SCORE
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.GameResult
import blackjack.view.GameResults
import blackjack.view.response.CardResponse

object BlackJackService {

    fun createPlayers(names: List<String>): List<Player> {
        val players = names.map { Player(it) }
        players.forEach { draw(it, 2) }
        return players
    }

    fun calculate(players: List<Player>): GameResults {
        val gameResults = players.map {
            GameResult(
                it.name,
                it.hand.map { card -> CardResponse.from(card) },
                AceDifferScoreCalculateStrategy.calculate(it.hand).score
            )
        }
        return GameResults(gameResults)
    }

    fun draw(player: Player, drawCount: Int) {
        repeat(drawCount) {
            player.addCard(Deck.draw())
        }
    }

    fun isBurst(player: Player): Boolean {
        return AceDifferScoreCalculateStrategy.calculate(player.hand).score > BLACK_JACK_SCORE
    }
}
