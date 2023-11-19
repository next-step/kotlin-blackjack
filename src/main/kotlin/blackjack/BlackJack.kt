package blackjack

import blackjack.domain.CardGenerator
import blackjack.domain.Participant
import blackjack.entity.ParticipantCards
import blackjack.entity.ParticipantState

class BlackJack(
    private val cardDeque: ParticipantCards,
    private val participant: Participant,
) {
    fun doBlackJack(enterY: Boolean): Participant {
        val newCard = CardGenerator.generateCard(GENERATE_SINGLE_CARD, cardDeque)
        return if (enterY) participant.drawCard(newCard) else participant
    }


    companion object {
        private const val GENERATE_SINGLE_CARD = 1
    }
}
