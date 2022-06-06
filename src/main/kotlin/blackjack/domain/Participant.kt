package blackjack.domain

data class Participant(
    val name: String
) {
    private val _playerCards = Cards()

    val cards: Cards
        get() = _playerCards

    fun addCard(card: Card) {
        _playerCards.addCard(card)
    }
}
