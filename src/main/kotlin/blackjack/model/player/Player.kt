package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.Cards

abstract class Player(open val name: String) {
    var cards = Cards(mutableListOf())

    abstract fun call(): Boolean

    fun drawCard(card: Card) {
        cards.add(card)
    }

    fun getDisplayCards(): String {
        return cards.getDisplayCards()
    }

    fun getTotalPointForBlackJack(): Int {
        return cards.getTotalPointForBlackJack()
    }
}
