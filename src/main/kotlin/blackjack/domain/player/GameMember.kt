package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.card.CardRank
import blackjack.domain.card.Deck

sealed interface GameMember {
    val name: String
    val cardHold: CardHold

    fun canDraw(): Boolean

    fun getPoints(): Int {
        val sum = cardHold.getTotalPoints()
        if (cardHold.getAllCards().any { card -> card.rank == CardRank.ACE } && sum <= THRESHOLD) {
            return sum + CardRank.ACE.getSoftHand()
        }
        return sum
    }

    fun getCardHoldSize(): Int {
        return cardHold.getCardsTotalSize()
    }

    fun drawCard(deck: Deck) {
        if (!canDraw()) {
            throw IllegalArgumentException("카드 숫자의 합이 21을 초과합니다.")
        }

        val card = deck.draw()
        if (card != null) {
            cardHold.add(card)
        }
    }

    companion object {
        const val THRESHOLD = 21
    }
}
