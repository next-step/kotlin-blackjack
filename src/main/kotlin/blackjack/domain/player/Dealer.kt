package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.card.Deck

class Dealer(
    override val name: String = "딜러",
    override var cardHold: CardHold = CardHold()
) : Player {
    override fun canDraw(): Boolean {
        return cardHold.getCardsTotalSize() <= 2 && cardHold.getTotalPoints() <= 16
    }

    override fun drawCard(deck: Deck) {
        if (!canDraw()) {
            throw IllegalArgumentException("딜러는 카드를 추가로 받을 수 없습니다")
        }

        val card = deck.draw()
        if (card != null) {
            cardHold = cardHold.add(card)
        }
    }
}
