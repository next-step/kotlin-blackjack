package blackjack.model

data class Player(
    val name: String,
) {
    val cards: Cards = initCards()

    init {
        validateName()
    }

    private fun validateName() {
        if (!name.matches(Regex("^[A-Za-z]+$"))) throw IllegalArgumentException("이름이 잘못 입력되었습니다.")
    }

    private fun initCards(): Cards = Cards(setOf(Card.takeRandomCard(), Card.takeRandomCard()))

    fun takeNewCard() {
        cards.addNewCard()
    }

    fun calculateResult(): Int {
        return cards.calculateCardValues()
    }
}
