package blackjack.controller

import blackjack.model.card.CardDeck
import blackjack.model.player.Player
import blackjack.model.strategy.RandomStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        val cards = CardDeck(RandomStrategy().shuffle())
        val players = InputView.inputPlayers().map(::Player)
        players.forEach { player ->
            repeat(2) {
                player.draw(cards.pop())
            }
        }
        OutputView.printPlayerInitStatus(players)

        players.forEach { player ->
            while (!player.state.isFinished()) {
                hitOrStay(player, cards)
                OutputView.printPlayerCards(player)
            }
        }
        OutputView.printResult(players)
    }

    private fun hitOrStay(player: Player, cards: CardDeck) {
        when (InputView.inputPlayerChoice(player.name)) {
            true -> player.draw(cards.pop())
            false -> player.stay()
        }
    }
}

fun main() {
    BlackjackController().start()
}
