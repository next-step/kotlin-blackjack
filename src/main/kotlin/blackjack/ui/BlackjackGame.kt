package blackjack.ui

import blackjack.Dealer
import blackjack.Deck
import blackjack.Money
import blackjack.Participant
import blackjack.Player
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
    val playerNames = inputPlayerNames()

    val deck = Deck()
    deck.shuffle()

    val dealer = Dealer(List(2) { deck.draw() })
    val players =
        playerNames.map { playerName ->
            val initialCards = List(2) { deck.draw() }
            Player(name = playerName, initialCards = initialCards)
        }

    players.forEach { player ->
        val betAmount = inputBetAmount(player)
        player.bet(betAmount)
    }

    announceAllParticipantsInitialized(dealer, players)

    val participants = listOf(dealer) + players

    revealAllParticipantsHeldCards(participants)

    players.forEach { participant ->
        while (!participant.isFinished()) {
            val answer = inputDrawAnswer(participant)
            when (answer) {
                DrawAnswer.Y -> {
                    val newCard = deck.draw()
                    participant.hit(newCard)
                    revealParticipantHeldCards(participant)
                }
                DrawAnswer.N -> {
                    participant.stay()
                }
            }
        }
        if (participant.isBust()) {
            announceBustParticipant(participant)
        }
        if (participant.isBlackjack()) {
            announceBlackjackParticipant(participant)
        }
    }

    if (dealer.shouldHit()) {
        announceDealerDrawOneMoreCard(dealer)
        val newCard = deck.draw()
        dealer.hit(newCard)
    }

    revealAllParticipantsHeldCardsAndScores(participants)

    val dealerProfit = dealer to dealer.calculateSelfProfit(players)
    val playerProfits =
        players.map {
            it to dealer.calculatePlayerProfit(it)
        }
    val profits: List<Pair<Participant, Money>> = listOf(dealerProfit) + playerProfits

    announceAllParticipantsProfits(profits)
}
