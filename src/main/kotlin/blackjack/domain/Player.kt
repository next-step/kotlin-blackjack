package blackjack.domain

data class Player(val name: String, val cards: Cards = Cards()) {

    private var isTurnFinished: Boolean = false

    init {
        require(name.isNotBlank()) { NAME_EXCEPTION }
    }

    fun addCard(card: Card) {
        cards.addCard(card)
    }

    fun addCards(cards: List<Card>) {
        cards.map { addCard(it) }
    }

    fun score(): Int {
        return cards.score()
    }

    fun finishedTurn() {
        isTurnFinished = true
    }

    fun canProceedTurn() = score() <= BlackJack.BLACKJACK_MAX_SCORE && !isTurnFinished

    companion object {
        private const val NAME_EXCEPTION = "이름을 정확하게 입력해주십시오"
    }
}
