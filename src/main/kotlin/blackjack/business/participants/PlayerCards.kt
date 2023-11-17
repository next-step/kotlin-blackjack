package blackjack.business.participants

import blackjack.business.canDrawCardStrategy.CanDrawCardStrategy
import blackjack.business.canDrawCardStrategy.PlayerCanDrawCardStrategy
import blackjack.business.card.Card
import blackjack.business.card.Rank
import blackjack.business.util.BlackJackConst.ACE_OFFSET
import blackjack.business.util.BlackJackConst.BLACKJACK

class PlayerCards(
    cards: List<Card> = listOf(),
    private val canDrawCardStrategy: CanDrawCardStrategy = PlayerCanDrawCardStrategy()
) {

    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    val size: Int
        get() = _cards.size

    operator fun get(i: Int): Card {
        return _cards[i]
    }

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

    fun canDrawCard(): Boolean {
        return canDrawCardStrategy.canDrawCard(sum())
    }

    fun isBust(): Boolean {
        return sum() > BLACKJACK
    }
}
