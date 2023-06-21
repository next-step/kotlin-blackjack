package blackjack.domain

data class User(val name: String) {
    lateinit var deck: Deck

    init {
        require(name.isNotBlank()) { EMPTY_NAME_ERROR_MESSAGE }
    }

    fun initDeck(deck: Deck) {
        this.deck = deck
    }

    fun addCard(card: Card) {
        deck.addCard(card)
    }

    companion object {
        private const val EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있을 수 없습니다"
    }
}
