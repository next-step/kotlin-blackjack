package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.card.CardHold.Companion.THRESHOLD
import blackjack.domain.card.Deck

class Player(val name: String) {
    var myCards: CardHold
        private set

    init {
        myCards = CardHold.init()
    }

    fun getTotalCardSize(): Int = myCards.cards.size

    fun getNowPoints(): Int = myCards.getPoints()

    fun showMyCards(): String = myCards.toString()

    fun canDraw(): Boolean = myCards.getPoints() <= THRESHOLD

    fun drawCard() {
        if (!canDraw()) {
            throw IllegalArgumentException("카드 숫자의 합이 21을 초과합니다.")
        }

        val card = Deck.draw()
        if (card != null) {
            myCards = myCards.add(card)
        }
    }
}
