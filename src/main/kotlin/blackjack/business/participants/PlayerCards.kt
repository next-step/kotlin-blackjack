package blackjack.business.participants

import blackjack.business.card.Card
import blackjack.business.card.Rank
import blackjack.business.util.BlackJackConst.ACE_OFFSET
import blackjack.business.util.BlackJackConst.BLACKJACK

class PlayerCards(
    cards: List<Card> = listOf()
) {

    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    val size: Int
        get() = _cards.size

    fun add(card: Card) {
        _cards.add(card)
    }

    fun sum(): Int {
        var sum = _cards.sumOf { it.rank.score }
        var aceCount = _cards.count { it.rank == Rank.ACE }
        while (sum + ACE_OFFSET <= BLACKJACK && aceCount > 0) {
            sum += ACE_OFFSET
            aceCount--
        }
        return sum
    }

    fun isBust(): Boolean {
        return sum() > BLACKJACK
    }

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }
}
