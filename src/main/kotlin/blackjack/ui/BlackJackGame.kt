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
        generateSequence {
            handlePlayerTurn(player, deck)
        }.toList()
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

private fun handlePlayerTurn(
    player: Player,
    deck: Deck,
): Unit? {
    if (isPlayerBust(player)) return null

    val answer = inputDrawAnswer(player)
    return when (answer) {
        DrawAnswer.Y -> {
            drawCardAndPrintPlayerHand(deck, player)
        }
        DrawAnswer.N -> {
            printPlayerHandWhenPlayerFirstTurn(player)
            null
        }
    }
}

private fun isPlayerBust(player: Player): Boolean {
    if (player.isBust()) {
        printPlayerBust(player)
        printPlayerSumOfHand(player)
        return true
    }
    return false
}

private fun drawCardAndPrintPlayerHand(
    deck: Deck,
    player: Player,
) {
    val newCard = deck.draw()
    player.receive(newCard)
    printPlayerNameWithHand(player)
    println()
}

private fun printPlayerHandWhenPlayerFirstTurn(player: Player) {
    if (isInitialState(player)) {
        printPlayerNameWithHand(player)
        println()
    }
}

private fun isInitialState(player: Player) = player.hand.size == 2
