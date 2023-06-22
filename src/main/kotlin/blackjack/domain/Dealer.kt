package blackjack.domain

import blackjack.domain.Game.Companion.INIT_TAKE_SIZE

class Dealer(override val deck: Deck = Deck()) : Player {
    private val interDeck = deck.copy()
    private var cardsReceived: Boolean = false
    override val name: String = "딜러"

    override fun addCard(card: Card) {
        require(isAddable()) {
            "현재 점수가 $THRESHOLD 이상이거나 카드를 분배받은 적이 있는 경우 추가할 수 없습니다."
        }

        if (interDeck.size == INIT_TAKE_SIZE && !cardsReceived) {
            cardsReceived = true
        }

        interDeck.add(card)
    }

    override fun addCardAll(values: Collection<Card>) {
        TODO("Not yet implemented")
    }

    override fun isAddable(): Boolean = interDeck.score() <= THRESHOLD && !cardsReceived

    override fun calculateScore(): Int = interDeck.score()

    override fun currentDeck(): Deck {
        TODO("Not yet implemented")
    }

    companion object {
        const val THRESHOLD = 16
    }
}
