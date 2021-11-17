package blackjack.service

import blackjack.domain.CardPoint.Companion.MAXIMUM_SUM_OF_CARD_POINTS

class PlayerCardAdditionDecider : ParticipantCardAdditionDecider {
    override fun canReceiveAdditionalCard(sum: Int): Boolean {
        return sum < MAXIMUM_SUM_OF_CARD_POINTS
    }
}
