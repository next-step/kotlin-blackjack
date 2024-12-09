package blackjack

sealed class Participant2(val name: String, initialCards: List<Card>) {
    private var state: State

    val cards: Cards
        get() = state.cards()

    init {
        require(name.isNotBlank()) {
            "이름은 빈 문자열일 수 없습니다: name=$name"
        }
        require(initialCards.size == INITIAL_CARD_SIZE) {
            "첫 패는 ${INITIAL_CARD_SIZE}장이어야 한다: initialCards.size=${initialCards.size}"
        }

        val cards = Cards(initialCards)

        state =
            when {
                cards.isBlackjack() -> Blackjack(cards)
                else -> Hit(cards)
            }
    }

    fun isFinished(): Boolean = state.isFinished()

    fun hit(card: Card) {
        state = state.draw(card)
    }

    fun stay() {
        state = state.stay()
    }

    fun sumOfCards(): Int = state.cards().sum()

    fun isBlackjack(): Boolean = state is Blackjack

    fun isBust(): Boolean = state is Bust

    companion object {
        private const val INITIAL_CARD_SIZE = 2
    }
}
