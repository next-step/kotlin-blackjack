package blackjack.model.participant

data class BlackjackParticipants(
    val participants: Collection<BlackjackParticipant>,
) {
    fun draw() {
        participants.forEach { it.draw() }
    }

    companion object {
        fun withDealer(players: Collection<BlackjackParticipant>, dealer: BlackjackParticipant): BlackjackParticipants {
            return BlackjackParticipants(players + dealer)
        }
    }
}
