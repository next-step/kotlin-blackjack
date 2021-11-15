package blackjack.domain

interface ParticipantCardAdditionDecider {
    fun canReceiveAdditionalCard(sum: Int): Boolean
}
