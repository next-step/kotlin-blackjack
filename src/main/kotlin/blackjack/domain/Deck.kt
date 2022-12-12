package blackjack.domain

class Deck(val values: Cards = Cards(Card.DECK)) {

    fun draw(): Card {
        return values.pick()
    }

    fun drawInitCards(): Cards {
        val cards = mutableListOf<Card>()
        repeat(START_CARD_SIZE) {
            cards.add(values.pick())
        }
        return Cards(cards.toList())
    }

    companion object {
        private const val START_CARD_SIZE = 2
    }
}
