package blackjack.domain.participant

data class Player(
    val name: String
) : Participant() {
    override fun isDrawable(): Boolean {
        return status == ParticipantStatus.HIT
    }
}
