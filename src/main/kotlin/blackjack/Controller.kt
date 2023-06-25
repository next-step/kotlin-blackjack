package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.score.GameResultManager
import blackjack.ui.InputView
import blackjack.ui.OutputView
import blackjack.ui.model.PlayerConverter

object Controller {
    fun start() {
        val deck = CardDeck()
        val playerNames = InputView.getPlayerName()
        val players = playerNames.map { Player(it, deck.draw(CardDeck.INIT_DRAW_SIZE)) }.let(::Players)
        val playerOutputModels = PlayerConverter.convert(players)
        OutputView.printInitState(playerOutputModels, deck.initDrawSize)

        players.players.forEach { player -> turn(deck, player) }
        val scoreResults = GameResultManager.getGameResults(players)
        OutputView.printResults(scoreResults)
    }

    private fun turn(deck: CardDeck, player: Player) {
        var isFirst = true
        generateSequence { deck.draw() }
            .takeWhile { isPlayerEligibleToHit(player) }
            .forEach { card ->
                player.draw(card)
                val playerOutputModel = PlayerConverter.convert(player)
                OutputView.printPlayersCard(playerOutputModel)
                isFirst = false
            }

        if (isFirst) {
            val playerOutputModel = PlayerConverter.convert(player)
            OutputView.printPlayersCard(playerOutputModel)
        }
    }

    private fun isPlayerEligibleToHit(player: Player): Boolean =
        InputView.isHit(player) && player.isNotBustPlayer() && player.isNotBlackJack()
}

fun main() {
    Controller.start()
}
