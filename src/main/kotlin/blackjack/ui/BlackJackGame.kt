package blackjack.ui

import blackjack.Dealer
import blackjack.Deck
import blackjack.GameJudge
import blackjack.Player
import blackjack.ui.ConsoleInput.Companion.inputDrawAnswer
import blackjack.ui.ConsoleInput.Companion.inputPlayerNames
import blackjack.ui.ConsoleOutput.Companion.announceDealerDrawOneMoreCard
import blackjack.ui.ConsoleOutput.Companion.announceGameResults
import blackjack.ui.ConsoleOutput.Companion.printAllParticipantsWithNameAndHand
import blackjack.ui.ConsoleOutput.Companion.printAllParticipantsWithNameAndHandAndResult
import blackjack.ui.ConsoleOutput.Companion.printParticipantWithNameAndHand
import blackjack.ui.ConsoleOutput.Companion.printPlayerBust
import blackjack.ui.ConsoleOutput.Companion.printPlayerSumOfHand
import blackjack.ui.ConsoleOutput.Companion.printShareInitialCardsToParticipants

fun main() {
    val playerNames = inputPlayerNames()

    val deck = Deck()
    deck.shuffle()

    val dealer = Dealer(List(2) { deck.draw() })
    val players = setupPlayers(playerNames, deck)

    printShareInitialCardsToParticipants(dealer, players)
    printAllParticipantsWithNameAndHand(listOf(dealer) + players)

    players.forEach { player ->
        generateSequence {
            handlePlayerTurn(player, deck)
        }.toList()
    }

    handleDealerTurn(dealer, deck)

    printAllParticipantsWithNameAndHandAndResult(listOf(dealer) + players)

    val gameJudge = GameJudge()
    val gameResults = gameJudge.judge(dealer, players)
    val dealerResult = gameJudge.summarizeDealerResult(gameResults)
    announceGameResults(gameResults, dealerResult)
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
            printParticipantWithNameAndHand(player)
        }
        DrawAnswer.N -> {
            if (player.isInitialState()) {
                printParticipantWithNameAndHand(player)
            }
            null
        }
    }
}

private fun handleDealerTurn(
    dealer: Dealer,
    deck: Deck,
) {
    if (dealer.shouldDrawCard()) {
        val newCard = deck.draw()
        dealer.receive(newCard)
        announceDealerDrawOneMoreCard()
    }
}
