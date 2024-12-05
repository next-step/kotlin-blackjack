package blackjack

class Player(
    val name: String,
    private val cards: PlayerCards = PlayerCards(),
) {
    init {
        validateName(name)
    }

    fun addCard(card: Card?): Boolean {
        return cards.addCard(card)
    }

    fun addCards(newCards: List<Card>): Boolean {
        return cards.addCards(newCards)
    }

    fun getCards(): List<Card> {
        return cards.cards
    }

    fun getCardsMaxSum(): Int {
        return cards.calculateCardsMaxSum()
    }

    fun couldDraw(): Boolean {
        return cards.calculateCardsMaxSum() < PlayerCards.GAME_LIMIT_NUMBER
    }

    fun isBust(): Boolean {
        return cards.calculateCardsMaxSum() == PlayerCards.ZERO
    }

    private fun validateName(name: String) {
        require(name.isNotBlank()) { "유저의 이름은 공백일 수 없습니다." }
    }
}
