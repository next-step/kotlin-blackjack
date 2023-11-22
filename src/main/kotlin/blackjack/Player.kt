package blackjack

import blackjack.card.BlackJackCard

class Player(
    val name: String,
) {
    val cards: MutableList<BlackJackCard> = mutableListOf()

    fun drawCard(cards: List<BlackJackCard>) {
        this.cards.addAll(cards)
    }
}
