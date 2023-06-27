package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.card.CardRank
import blackjack.domain.card.Deck
import blackjack.domain.rule.Score

sealed interface Player {
    val name: String
    val cardHold: CardHold

    fun canDraw(): Boolean

    fun compareScore(other: Player): Score

    fun getPoints(): Int {
        val sum = cardHold.getTotalPoints()
        if (cardHold.getAllCards().any { card -> card.rank == CardRank.ACE } && sum <= THRESHOLD) {
            return sum + CardRank.ACE.getSoftHand()
        }
        return sum
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
