package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.card.CardHold.Companion.THRESHOLD
import blackjack.domain.card.Deck

class Player(
    val name: String,
    cardHold: CardHold = CardHold()
) {
    var myCards = cardHold
        private set

    fun getTotalCardSize(): Int = myCards.cards.size

    fun getNowPoints(): Int = myCards.getPoints()

    fun canDraw(): Boolean = myCards.getPoints() <= THRESHOLD

    fun drawCard(deck: Deck) {
        if (!canDraw()) {
            throw IllegalArgumentException("카드 숫자의 합이 21을 초과합니다.")
        }

        val card = deck.draw()
        if (card != null) {
            myCards = myCards.add(card)
        }
    }
}
