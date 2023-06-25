package blackjack.domain

private const val NAME_EXCEPTION = "이름을 정확하게 입력해주십시오"

data class Player(val name: String, val cards: Cards = Cards()) {
    init {
        require(!name.isNullOrBlank()) { NAME_EXCEPTION }
    }

    fun draw() {
        cards.addCard(Card.draw())
    }

    fun canPlay(): Boolean {
        return cards.score() <= BLACKJACK_MAX_SCORE
    }
}
