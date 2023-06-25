package blackjack.domain

private const val NAME_EXCEPTION = "이름을 정확하게 입력해주십시오"

data class Player(val name: String, val cards: MutableList<Card> = mutableListOf()) {
    init {
        require(!name.isNullOrBlank()) { NAME_EXCEPTION }
    }

    fun contains(card: Card): Boolean {
        return cards.contains(card)
    }

    fun addCard(card: Card) {
        cards.add(card)
    }
}
