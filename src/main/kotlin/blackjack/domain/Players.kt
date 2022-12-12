package blackjack.domain

class Players(val players: List<Player>) {

    constructor(names: List<String>, cardDeck: CardDeck) : this(
        names.map { name ->
            val initCards = List(INIT_HIT_COUNT) { cardDeck.draw() }
            val cards = Cards(initCards)

            Player(name, cards)
        }
    )

    companion object {
        private const val INIT_HIT_COUNT = 2
    }
}
