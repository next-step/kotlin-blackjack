package blackjack.domain

class Hands(val cards: List<Card>) {
    init {
        require(cards.size == INIT_CARD_SIZE) { "시작 카드는 ${INIT_CARD_SIZE}장이어야 합니다." }
    }

    companion object {
        const val INIT_CARD_SIZE = 2
    }
}
