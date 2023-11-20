package blackjack.domain.player

import blackjack.domain.card.CardSet

class PlayerBuilder {
    private lateinit var name: String
    private var cardSet: CardSet = CardSet.empty()

    fun name(name: String) {
        this.name = name
    }

    fun cardSet(cardSet: CardSet) {
        this.cardSet = cardSet
    }

    fun build(): Player {
        return Player(name, cardSet)
    }
}

fun player(block: PlayerBuilder.() -> Unit): Player {
    return PlayerBuilder().apply(block).build()
}
