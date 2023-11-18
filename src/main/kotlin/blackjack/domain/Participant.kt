package blackjack.domain

import blackjack.entity.Cards
import blackjack.entity.ParticipantState

data class Participant(
    val name: String,
    val cards: Cards,
) {
    var participantState: ParticipantState = calculateParticipantState()
        private set

    private fun calculateParticipantState(): ParticipantState {
        if (participantState is ParticipantState.STAND) return participantState
        return when (sumOfCardNumbers()) {
            in MINIMUM_HIT_NUMBER..MAXIMUM_HIT_NUMBER -> ParticipantState.HIT
            BLACK_JACK -> ParticipantState.BLACKJACK
            else -> ParticipantState.BUST
        }
    }

    fun setParticipantState(state: ParticipantState.STAND) {
        participantState = state
    }

    fun copyNewCards(newCards: Cards): Participant = copy(cards = newCards)

    fun sumOfCardNumbers(): Int = if (cards.cardsContainACard) {
        sumOfCardsWithA()
    } else {
        cards.sumOfCards
    }

    /**
     * A가 가질 수 있는 상태가 1, 11이지만 선택은 참여자가 선택한다고 생각해서 이곳에 함수를 만들었습니다.
     */
    private fun sumOfCardsWithA(): Int = if (cards.sumOfCards + SUPER_A_PLUS_NUMBER > BLACK_JACK) {
        cards.sumOfCards
    } else {
        cards.sumOfCards + SUPER_A_PLUS_NUMBER
    }

    companion object {
        private const val MINIMUM_HIT_NUMBER = 1
        private const val MAXIMUM_HIT_NUMBER = 20
        private const val BLACK_JACK = 21
        private const val SUPER_A_PLUS_NUMBER = 10
    }
}
