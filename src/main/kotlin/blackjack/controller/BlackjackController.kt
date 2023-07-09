package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Participant
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController {
    private val deck = Deck()

    fun inputPlayers(): List<Player> {
        return InputView.players().map { Player(it) }
    }

    fun drawInitialCards(participants: Participants) {
        participants.forEach {
            it.addCard(deck.drawCard())
            it.addCard(deck.drawCard())
        }
        println()
    }

    fun printInitialCards(participants: Participants) {
        ResultView.initialCards(participants)
        println()
    }

    fun drawMoreCard(participant: Participant): Boolean {
        if (!participant.canDrawMoreCard()) {
            return false
        }
        if (participant is Dealer) {
            participant.addCard(deck.drawCard())
            return false
        }
        if (InputView.drawMoreCard(participant.name)) {
            participant.addCard(deck.drawCard())
            ResultView.participantAndCards(participant)
            return true
        }
        return false
    }

    fun printResult(participants: Participants) {
        ResultView.result(participants)
        println()
    }

    fun printFinalResult(participants: Participants) {
        ResultView.finalResult(participants)
    }
}
