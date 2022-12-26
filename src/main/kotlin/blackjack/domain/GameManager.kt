package blackjack.domain

import blackjack.application.Deck
import blackjack.controller.InputFilter
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.role.Role
import blackjack.dto.ParticipantDto
import blackjack.view.ResultView

object GameManager {
    private const val MINIMUM_NUMBER_OF_CARDS = 2

    fun checkBlackjack(participants: Participants): Boolean {
        var result = false
        participants.getAll().forEach {
            result = result or it.isBlackjack()
        }
        return result
    }

    fun play(participants: Participants, deck: Deck): Participants {
        val newPlayers = participants.getPlayers().map {
            doHitOrStay(it, deck)
        }
        val newDealers = doHitOrStay(participants.getDealer(), deck)
        return Participants(newDealers, *newPlayers.toTypedArray())
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
