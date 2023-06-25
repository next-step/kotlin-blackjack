package blackjack.domain

data class User(val name: String, val deck: Deck) {

    init {
        require(name.isNotBlank()) { EMPTY_NAME_ERROR_MESSAGE }
    }

    fun addCard(card: Card) {
        deck.addCard(card)
    }

    fun calculatePointOrNull(): Int? {
        return deck.calculatePointOrNull()
    }

    companion object {
        private const val EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있을 수 없습니다"
    }
}
