package blackjack.domain.player

import blackjack.domain.card.CardDeck

class Player private constructor(
    val name: Name,
    private val cardsInHand: CardsInHand
) {

    fun hit(cardDeck: CardDeck) {
        cardsInHand.add(cardDeck.draw())
    }

    companion object {
        fun sit(name: Name): Player = Player(name, CardsInHand(emptyList()))
    }
}
