package blackjack.domain

import blackjack.application.Deck
import blackjack.controller.InputFilter
import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.role.Role
import blackjack.dto.ParticipantDto
import blackjack.view.ResultView

object GameManager {
    private const val MINIMUM_NUMBER_OF_CARDS = 2

    fun checkBlackjack(participants: Participants): Boolean {
        var result = false
        participants.values.forEach {
            result = result or it.isBlackjack()
        }
        return result
    }

    fun play(participants: Participants, deck: Deck): Participants {
        val newPlayers = participants.values.map {
            doHitOrStay(it, deck)
        }
        return Participants(newPlayers)
    }

    private fun doHitOrStay(role: Role, deck: Deck): Role {
        var newPlayer = role
        while (InputFilter.inputHitOrStay(ParticipantDto.from(role).name)) {
            newPlayer = role.draw(deck.getCard())
            ResultView.printParticipantCards(ParticipantDto.from(newPlayer))
        }
        if (!role.isBust() || !role.isBlackjack()) {
            newPlayer = role.stay()
        }
        if (role.getCardsSize() == MINIMUM_NUMBER_OF_CARDS) {
            ResultView.printParticipantCards(ParticipantDto.from(newPlayer))
        }
        return newPlayer
    }
}
