package blackjack.model

data class BlackjackParticipants(
    val participants: Collection<BlackjackParticipant>,
) {
    fun draw(deck: CardDeck) {
        participants.forEach { it.draw(deck) }
    }
}
