package blackjack.model

data class BlackjackParticipants(
    val participants: Collection<BlackjackParticipant>,
) {
    fun forEach(action: (BlackjackParticipant) -> Unit) {
        participants.forEach(action)
    }
}
