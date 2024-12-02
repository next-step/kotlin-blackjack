package blackjack

class Player(
    val name: String,
    private val cards: PlayerCards = PlayerCards(),
) {
    init {
        validateName(name)
    }

    fun addCard(card: Card?) {
        cards.addCard(card)
    }

    fun getCards(): List<Card> {
        return cards.getCards()
    }

    fun getCardsMaxSum(): Int {
        return cards.calculateCardsMaxSum()
    }

    fun couldAddCard(): Boolean {
        return false
    }

    private fun validateName(name: String) {
        require(name.isNotBlank()) { "유저의 이름은 공백일 수 없습니다." }
    }
}
