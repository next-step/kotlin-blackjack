package blackjack.domain

data class Player(val name: String, val cards: Cards = Cards()) {
    init {
        require(!name.isNullOrBlank()) { NAME_EXCEPTION }
    }

    fun draw() {
        cards.addCard(Card.draw())
    }

    fun score(): Int {
        return cards.score()
    }

    companion object {
        private const val NAME_EXCEPTION = "이름을 정확하게 입력해주십시오"
    }
}
