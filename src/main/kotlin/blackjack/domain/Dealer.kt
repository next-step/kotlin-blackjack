package blackjack.domain

import blackjack.domain.Game.Companion.INIT_TAKE_SIZE

class Dealer(override val deck: Deck = Deck()) : Player {
    override val name: String = DEFAULT_DEALER_NAME

    private val interDeck = deck.copy()

    private var cardsReceived: Boolean = false

    override fun addCard(card: Card) {
        require(isAddable()) {
            "현재 점수가 $THRESHOLD 이상이거나 카드를 분배받은 적이 있는 경우 추가할 수 없습니다."
        }

        if (interDeck.size == INIT_TAKE_SIZE && !cardsReceived) {
            cardsReceived = true
        }

        interDeck.add(card)
    }

    override fun addCardAll(values: Collection<Card>) = values.forEach(::addCard)

    override fun isAddable(): Boolean = interDeck.score() <= THRESHOLD && !cardsReceived

    override fun calculateScore(): Int = interDeck.score()

    override fun currentDeck(): Deck = interDeck.copy()

    companion object {
        const val THRESHOLD = 16
        const val DEFAULT_DEALER_NAME = "딜러"
    }
}
