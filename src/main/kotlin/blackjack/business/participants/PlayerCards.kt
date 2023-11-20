package blackjack.business.participants

import blackjack.business.card.Card
import blackjack.business.card.Rank
import blackjack.business.util.BlackJackConst.ACE_OFFSET
import blackjack.business.util.BlackJackConst.BLACKJACK

class PlayerCards(val cards: List<Card> = listOf()) {

    constructor(vararg cards: Card) : this(cards.toList())

    val size: Int
        get() = cards.size

    fun sum(): Int {
        var sum = cards.sumOf { it.rank.score }
        var aceCount = cards.count { it.rank == Rank.ACE }
        while (sum + ACE_OFFSET <= BLACKJACK && aceCount > 0) {
            sum += ACE_OFFSET
            aceCount--
        }
        return sum
    }

    fun add(card: Card): PlayerCards {
        return PlayerCards(cards + card)
    }

    fun isBust(): Boolean {
        return sum() > BLACKJACK
    }

    fun addAll(cards: List<Card>): PlayerCards {
        return PlayerCards(this.cards + cards)
    }
}
