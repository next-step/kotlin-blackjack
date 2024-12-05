package blackjack.domain

class Player(
    val name: String,
    private val hand: PlayerCards = PlayerCards(),
) {
    init {
        validateName(name)
    }

    fun addCard(card: Card?): Boolean {
        return hand.addCard(card)
    }

    fun addCards(newCards: List<Card>): Boolean {
        return hand.addCards(newCards)
    }

    fun getCards(): List<Card> {
        return hand.cards
    }

    fun getCardsMaxSum(): Int {
        return hand.calculateCardsMaxSum()
    }

    fun couldDraw(): Boolean {
        return hand.calculateCardsMaxSum() < PlayerCards.GAME_LIMIT_NUMBER
    }

    fun isBust(): Boolean {
        return hand.calculateCardsMaxSum() == PlayerCards.ZERO
    }

    fun isBlackJack(): Boolean {
        return hand.calculateCardsMaxSum() == PlayerCards.GAME_LIMIT_NUMBER
    }

    private fun validateName(name: String) {
        require(name.isNotBlank()) { "유저의 이름은 공백일 수 없습니다." }
    }
}
