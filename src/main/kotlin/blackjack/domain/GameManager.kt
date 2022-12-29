package blackjack.domain

import blackjack.application.Deck
import blackjack.controller.InputFilter
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.result.Result
import blackjack.domain.participant.state.role.Role
import blackjack.dto.ParticipantDto
import blackjack.view.ResultView

object GameManager {
    private const val MINIMUM_NUMBER_OF_CARDS = 2

    fun isBlackjack(participants: Participants): Boolean {
        var result = false
        participants.getAll().forEach {
            result = result or it.isBlackjack()
        }
        return result
    }

    fun play(participants: Participants, deck: Deck): Participants {
        if (isBlackjack(participants)) {
            return participants
        }
        val newPlayers = participants.getPlayers().map {
            doHitOrStay(it, deck)
        }
        val newDealers = doHitOrStay(participants.getDealer(), deck)
        return Participants(newDealers, *newPlayers.toTypedArray())
    }

    fun calculateResult(participants: Participants): Map<Role, Result> {
        if (participants.getDealer().isBlackjack()) {
            return participants.getPlayers().associateWith { Result.Lose }
        }

        if (participants.getDealer().isBust()) {
            return participants.getPlayers().associateWith { Result.Win }
        }
        return participants.getPlayers().associateWith { it.calculateResult(participants.getDealer().getScore()) }
    }

    private fun doHitOrStay(role: Role, deck: Deck): Role {
        if (role.isDealer()) {
            return doDealerHitOrStay(role, deck)
        }
        return doPlayerHitOrStay(role, deck)
    }

    private fun doDealerHitOrStay(role: Role, deck: Deck): Role {
        if (role.getScore() <= Dealer.STOP_SCORE) {
            ResultView.printDealerDrawMessage()
            return role.draw(deck.getCard())
        }
        return role.stay()
    }

    private fun doPlayerHitOrStay(role: Role, deck: Deck): Role {
        var newPlayer = role
        while (InputFilter.inputHitOrStay(ParticipantDto.from(role).name)) {
            newPlayer = role.draw(deck.getCard())
            ResultView.printParticipantCards(ParticipantDto.from(newPlayer))
        }
        if (!newPlayer.isBust() && !newPlayer.isBlackjack()) {
            newPlayer = role.stay()
        }
        if (newPlayer.getCardsSize() == MINIMUM_NUMBER_OF_CARDS) {
            ResultView.printParticipantCards(ParticipantDto.from(newPlayer))
        }
        return newPlayer
    }
}
