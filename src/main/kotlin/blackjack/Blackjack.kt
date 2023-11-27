package blackjack

import blackjack.domain.cards.Deck
import blackjack.domain.cards.HandCards
import blackjack.domain.player.Hand
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerState
import blackjack.view.InputView
import blackjack.view.ResultView
import blackjack.view.UserInputView

class Blackjack(
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    private val deck = Deck.fullDeck()

    init {
        deck.shuffle()
    }

    fun simulate() {
        val playerNames = inputView.getPlayerNames()

        val players = createPlayers(playerNames)

        resultView.printInitialState(players)

        players.forEach { player ->
            processPlayerTurn(player)
        }

        resultView.printResult(players)
    }

    private fun processPlayerTurn(player: Player) {
        while (player.state == PlayerState.Hit) {
            val command = inputView.getPlayerCommand(player.name)
            player.play(command == "y")
            resultView.printPlayer(player)
        }
    }

    private fun createPlayers(playerNames: List<String>): List<Player> {
        return playerNames.map { Player(it, Hand(HandCards(mutableListOf(deck.draw(), deck.draw())))) }
    }

    private fun Player.play(isHit: Boolean) {
        if (isHit) {
            hit()
            val card = deck.draw()
            addCard(card)
        } else {
            stay()
        }
    }
}

fun main() {
    Blackjack(
        UserInputView(),
        ResultView(),
    ).simulate()
}
