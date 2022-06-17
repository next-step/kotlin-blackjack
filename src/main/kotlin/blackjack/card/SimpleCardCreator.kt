package blackjack.card

object SimpleCardCreator {

    fun startCard(): Cards {
        return Cards(
            * Suit.values().flatMap { suit ->
                CardSymbol.values()
                    .map { symbol -> Card(suit, symbol) }
            }.toTypedArray()
        )
    }
}
