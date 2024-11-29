package blackjack.ui

import blackjack.Deck
import blackjack.Player
import blackjack.ui.ConsoleInput.Companion.inputDrawAnswer
import blackjack.ui.ConsoleInput.Companion.inputPlayerNames
import blackjack.ui.ConsoleOutput.Companion.printPlayerBust
import blackjack.ui.ConsoleOutput.Companion.printPlayerNameWithHand
import blackjack.ui.ConsoleOutput.Companion.printPlayerSumOfHand
import blackjack.ui.ConsoleOutput.Companion.printShareInitialCardsToPlayers

fun main() {
    val playerNames = inputPlayerNames()
    println()

    val deck = Deck()
    deck.shuffle()

    val players = setupPlayers(playerNames, deck)

    printShareInitialCardsToPlayers(playerNames)
    players.forEach { player ->
        printPlayerNameWithHand(player)
        println()
    }
    println()

    players.forEach { player ->
        while (true) {
            val answer = inputDrawAnswer(player)
            if (answer == DrawAnswer.N) {
                if (isInitialState(player)) {
                    printPlayerNameWithHand(player)
                    println()
                }
                break
            } else if (answer == DrawAnswer.Y) {
                val newCard = deck.draw()
                player.receive(newCard)
                printPlayerNameWithHand(player)
                println()
                if (player.isBust()) {
                    printPlayerBust(player)
                    printPlayerSumOfHand(player)
                    break
                }
            }
        }
    }
    println()

    players.forEach { player ->
        printPlayerNameWithHand(player)
        printPlayerSumOfHand(player)
    }
}

private fun setupPlayers(
    playerNames: List<String>,
    deck: Deck,
) = playerNames.map { playerName ->
    val initialCards = List(2) { deck.draw() }
    Player(name = playerName, initialCards = initialCards)
}

private fun isInitialState(player: Player) = player.hand.size == 2
