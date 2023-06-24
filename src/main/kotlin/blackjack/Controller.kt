package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.score.ScoreManager
import blackjack.ui.InputView
import blackjack.ui.OutputView

object Controller {
    fun start() {
        val deck = CardDeck.create()
        val playerNames = InputView.getPlayerName()
        val players = playerNames.map { Player(it, deck.initDraw()) }.let(::Players)
        OutputView.printInitState(players, deck.initDrawSize)

        players.players
            .forEach { player ->
                var isFirst = true
                generateSequence { deck.draw() }
                    .takeWhile { isPlayerEligibleToHit(player) }
                    .forEach { card ->
                        player.draw(card)
                        OutputView.printPlayersCard(player)
                        isFirst = false
                    }

                if (isFirst) {
                    OutputView.printPlayersCard(player)
                }
            }
        val scoreResults = ScoreManager.getGameResults(players)
        OutputView.printResults(scoreResults)
    }

    private fun isPlayerEligibleToHit(player: Player): Boolean =
        InputView.isHit(player) && ScoreManager.isNotBustPlayer(player) && ScoreManager.isNotBlackJack(player)
}

fun main() {
    Controller.start()
}
