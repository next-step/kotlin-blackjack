package blackjack.domain.participant

data class Player(
    override val name: String,
    override val money: Money
) : Participant() {
    override fun isDrawable(): Boolean {
        return status == ParticipantStatus.HIT
    }
}
