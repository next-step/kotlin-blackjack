package blackjack.ui

import blackjack.Deck
import blackjack.Player
import blackjack.ui.ConsoleInput.Companion.inputDrawAnswer
import blackjack.ui.ConsoleInput.Companion.inputPlayerNames
import blackjack.ui.ConsoleOutput.Companion.printAllPlayersWithNameAndHand
import blackjack.ui.ConsoleOutput.Companion.printAllPlayersWithNameAndHandAndResult
import blackjack.ui.ConsoleOutput.Companion.printPlayerBust
import blackjack.ui.ConsoleOutput.Companion.printPlayerSumOfHand
import blackjack.ui.ConsoleOutput.Companion.printPlayerWithNameAndHand
import blackjack.ui.ConsoleOutput.Companion.printShareInitialCardsToPlayers

fun main() {
    val playerNames = inputPlayerNames()

    val deck = Deck()
    deck.shuffle()

    val players = setupPlayers(playerNames, deck)

    printShareInitialCardsToPlayers(playerNames)
    printAllPlayersWithNameAndHand(players)

    players.forEach { player ->
        generateSequence {
            handlePlayerTurn(player, deck)
        }.toList()
    }

    printAllPlayersWithNameAndHandAndResult(players)
}

private fun setupPlayers(
    playerNames: List<String>,
    deck: Deck,
) = playerNames.map { playerName ->
    val initialCards = List(2) { deck.draw() }
    Player(name = playerName, initialCards = initialCards)
}

private fun handlePlayerTurn(
    player: Player,
    deck: Deck,
): Unit? {
    if (player.isBust()) {
        printPlayerBust(player)
        printPlayerSumOfHand(player)
        return null
    }

    val answer = inputDrawAnswer(player)
    return when (answer) {
        DrawAnswer.Y -> {
            val newCard = deck.draw()
            player.receive(newCard)
            printPlayerWithNameAndHand(player)
        }
        DrawAnswer.N -> {
            if (player.isInitialState()) {
                printPlayerWithNameAndHand(player)
            }
            null
        }
    }
}
