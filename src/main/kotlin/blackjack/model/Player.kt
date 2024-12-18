package blackjack.model

data class Player(
    val name: String,
    private val deck: Deck
) {
    val cards: Cards = initCards()

    init {
        validateName()
    }

    private fun validateName() {
        require(name.matches(ALPHABET_REGEX)) {
            "이름이 잘못 입력되었습니다."
        }
    }

    private fun initCards(): Cards = Cards(deck, setOf(deck.takeRandomCard(), deck.takeRandomCard()))

    fun takeNewCard() {
        cards.addNewCard()
    }

    fun calculateResult(): Int {
        return cards.calculateCardValues()
    }

    private companion object {
        val ALPHABET_REGEX = Regex("^[A-Za-z]+$")
    }
}

