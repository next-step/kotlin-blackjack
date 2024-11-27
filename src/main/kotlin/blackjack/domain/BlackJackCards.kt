package blackjack.domain

class BlackJackCards(val cards: List<BlackJackCard>) {
    fun get(
        shape: BlackJackCardShape,
        number: BlackJackCardNumber,
    ): BlackJackCard {
        return cards.filter { it.shape.equals(shape) && it.number.equals(number) }.first()
    }
}

class BlackJackCardsBuilder() {
    var cards: MutableList<BlackJackCard> = mutableListOf()

    init {
        BlackJackCardShape.entries.forEach { shape ->
            BlackJackCardNumber.entries.forEach { number ->
                cards.add(
                    BlackJackCard(shape, number),
                )
            }
        }
    }

    fun build(): BlackJackCards {
        return BlackJackCards(cards)
    }
}
