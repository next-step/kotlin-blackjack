package blackjack

class Player(val name: String, initialCards: List<Card>) {
    private val _hand = Hand(initialCards)
    val hand: List<Card>
        get() = _hand.cards

    init {
        require(name.isNotBlank()) {
            "이름은 빈 문자열일 수 없습니다: name=$name"
        }
        require(initialCards.size == INITIAL_HAND_LENGTH) {
            "첫 패는 ${INITIAL_HAND_LENGTH}장이어야 한다: initialCards.size=${initialCards.size}"
        }
    }

    fun isInitialState(): Boolean = _hand.cards.size == 2

    fun receive(newCard: Card) {
        _hand.add(newCard)
    }

    fun sumOfHand(): Int = _hand.sumOfHand()

    fun isBust(): Boolean = _hand.isBust()

    companion object {
        private const val INITIAL_HAND_LENGTH = 2
    }
}
