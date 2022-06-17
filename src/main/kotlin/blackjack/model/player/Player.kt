package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.Cards

class Player private constructor(
    val name: PlayerName,
    val cards: Cards = Cards()
) {
    val cardSize
        get() = cards.size

    fun receiveCard(card: Card) = cards.addOne(card)

    companion object {
        fun from(name: String) = Player(PlayerName(name))
    }
}
