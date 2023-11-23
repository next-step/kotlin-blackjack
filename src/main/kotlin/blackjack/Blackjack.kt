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
            player.play()
        }

        resultView.printResult(players)
    }

    private fun createPlayers(playerNames: List<String>): List<Player> {
        return playerNames.map { Player(it, Hand(HandCards(mutableListOf(deck.draw(), deck.draw())))) }
    }

    private fun Player.play() {

        fun processCommand(command: String) {
            if (command == "y") {
                hit()
                val card = deck.draw()
                addCard(card)
                println("$name: ${hand.handCards}")
            } else {
                stay()
            }
        }

        while (state == PlayerState.Hit) {
            println("${name}은 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            val command = readln().trim()
            processCommand(command)
        }
        println("$name: ${hand.handCards}")
        println("$state")
    }
}

fun main() {
    Blackjack(
        UserInputView(),
        ResultView(),
    ).simulate()
}
