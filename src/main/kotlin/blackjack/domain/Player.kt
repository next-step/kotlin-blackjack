package blackjack.domain

class Player(
    override val name: String,
    override val hands: Hands = Hands(),
    override var status: ParticipantStatus = ParticipantStatus.PLAYING,
) : Participant(name, hands, status) {
    fun toStay() {
        status = ParticipantStatus.STAY
    }

    override fun handleStatus() {
        status =
            when {
                Rule.checkBurst(score) -> ParticipantStatus.BURST
                Rule.checkBlackjack(score) -> ParticipantStatus.STAY
                else -> ParticipantStatus.PLAYING
            }
    }
}
