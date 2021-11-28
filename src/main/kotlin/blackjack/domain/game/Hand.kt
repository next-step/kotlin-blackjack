package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Score

interface Hand {

    val score: Score
        get() = cards.score

    val cards: Cards

    fun canHit(): Boolean

    fun getResult(): GameResult

    fun hit(card: Card): Hand
}

abstract class PlayingHand : Hand {

    protected abstract fun hitByGamer(card: Card): Hand

    final override fun canHit() = true

    final override fun getResult() = Stay(cards)

    final override fun hit(card: Card): Hand {
        cards.add(card)

        if (cards.isBust()) {
            return Bust(cards)
        }
        if (cards.isBlackJack()) {
            return BlackJack(cards)
        }
        return hitByGamer(card)
    }
}

