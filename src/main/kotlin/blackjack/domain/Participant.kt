package blackjack.domain

data class Participant(
    val name: String
) {
    private val _playerCards = ParticipantCard()

    val cards: ParticipantCard
        get() = _playerCards

    fun addCard(card: Card) {
        _playerCards.add(card)
    }
}
