package blackjack

class Player(
    val name: String,
    private val cards: PlayerCards = PlayerCards(),
) {
    init {
        validateName(name)
    }

    fun addCard(card: Card?): Boolean {
        return if (couldAddCard()) {
            cards.addCard(card)
        } else {
            false
        }
    }

    fun getCards(): List<Card> {
        return cards.getCards()
    }

    fun getCardsMaxSum(): Int {
        return cards.calculateCardsMaxSum()
    }

    private fun couldAddCard(): Boolean {
        return cards.calculateCardsMaxSum() < PlayerCards.GAME_LIMIT_NUMBER
    }

    private fun validateName(name: String) {
        require(name.isNotBlank()) { "유저의 이름은 공백일 수 없습니다." }
    }
}
