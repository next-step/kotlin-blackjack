package blackjack.ui

import blackjack.BlackjackService
import blackjack.Deck
import blackjack.Money
import blackjack.Participant
import blackjack.ui.ConsoleInput.Companion.inputBetAmount
import blackjack.ui.ConsoleInput.Companion.inputDrawAnswer
import blackjack.ui.ConsoleInput.Companion.inputPlayerNames
import blackjack.ui.ConsoleOutput.Companion.announceAllParticipantsInitialized
import blackjack.ui.ConsoleOutput.Companion.announceAllParticipantsProfits
import blackjack.ui.ConsoleOutput.Companion.announceBlackjackParticipant
import blackjack.ui.ConsoleOutput.Companion.announceBustParticipant
import blackjack.ui.ConsoleOutput.Companion.announceDealerDrawOneMoreCard
import blackjack.ui.ConsoleOutput.Companion.revealAllParticipantsHeldCards
import blackjack.ui.ConsoleOutput.Companion.revealAllParticipantsHeldCardsAndScores
import blackjack.ui.ConsoleOutput.Companion.revealParticipantHeldCards

fun main() {
    val deck = Deck().apply { shuffle() }
    val service = BlackjackService(deck)

    val playerNames = inputPlayerNames()

    val (dealer, players) = service.initializeParticipants(playerNames)

    service.betAllPlayers(players) { player -> inputBetAmount(player) }

    announceAllParticipantsInitialized(dealer, players)

    val participants = listOf(dealer) + players

    revealAllParticipantsHeldCards(participants)

    service.handleAllPlayersTurn(
        players = players,
        drawAnswer = { inputDrawAnswer(it) },
        revealParticipantHeldCards = { revealParticipantHeldCards(it) },
        onBust = { announceBustParticipant(it) },
        onBlackjack = { announceBlackjackParticipant(it) },
    )

    service.handleDealerTurn(
        dealer = dealer,
        onUnderOverOutput = { announceDealerDrawOneMoreCard(it) },
        onBust = { announceBustParticipant(it) },
        onBlackjack = { announceBlackjackParticipant(it) },
    )

    revealAllParticipantsHeldCardsAndScores(participants)

    val profits: List<Pair<Participant, Money>> = service.calculateAllPlayerProfits(dealer, players)

    announceAllParticipantsProfits(profits)
}
