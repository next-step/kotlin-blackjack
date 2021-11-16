package blackjack.service

interface ParticipantCardAdditionDecider {
    fun canReceiveAdditionalCard(sum: Int): Boolean
}
