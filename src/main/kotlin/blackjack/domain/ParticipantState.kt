package blackjack.domain

sealed class ParticipantState {
    abstract fun isBurst(): Boolean
}

object Alive : ParticipantState() {
    override fun isBurst(): Boolean = false
}

object Burst : ParticipantState() {
    override fun isBurst(): Boolean = true
}
