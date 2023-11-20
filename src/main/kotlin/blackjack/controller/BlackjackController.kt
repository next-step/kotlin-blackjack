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
                player.draw(cards.get())
            }
        }
        OutputView.printPlayerInitStatus(players)

        players.forEach { player ->
            if (!isBlackJack(player)) {
                play(player, cards)
            }
        }
        OutputView.printResult(players)
    }

    private fun isBlackJack(player: Player) = player.state.isFinished()

    private fun play(player: Player, cards: CardDeck) {
        while (InputView.inputPlayerChoice(player.name)) {
            player.draw(cards.get())
            OutputView.printPlayerCards(player)
        }
    }
}

fun main() {
    BlackjackController().start()
}
