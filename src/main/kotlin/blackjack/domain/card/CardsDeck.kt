package blackjack.domain.card

class CardsDeck {
    private val cards = CardDenomination.values().flatMap { cardDenomination ->
        CardPattern.values().map { cardPattern ->
            Card(
                pattern = cardPattern.name,
                denomination = cardDenomination.denomination,
                value = cardDenomination.value
            )
        }
    }

    init {
        cards.forEach {
            println(it)
        }
    }
}
