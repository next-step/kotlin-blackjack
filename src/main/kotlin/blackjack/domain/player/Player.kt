package blackjack.domain.player

import blackjack.domain.trump.Card
import blackjack.domain.trump.Cards
import blackjack.domain.trump.MAX_SUM_OF_CARDS

class Player(val name: Name, val cards: Cards, val finished: Boolean = false) {

    fun isAbleToDraw(): Boolean {
        return cards.score() <= MAX_SUM_OF_CARDS
    }

    fun score(): Int {
        return cards.score()
    }

    fun death(): Player {
        return Player(name, cards, true)
    }

    fun renewal(card: Card): Player {
        return renewal(cards.add(card))
    }

    fun renewal(cards: Cards): Player {
        return Player(name, cards)
    }

    companion object {
        fun from(name: String): Player {
            return Player(Name.from(name), Cards(listOf()))
        }
    }
}
