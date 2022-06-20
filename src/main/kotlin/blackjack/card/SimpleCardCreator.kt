package blackjack.card

object SimpleCardCreator {

    fun startCard(): PlayCards {
        val cards = Suit.values().flatMap { suit ->
            CardSymbol.values()
                .map { symbol -> Card(suit, symbol) }
        }.toTypedArray()
            .also { it.shuffle() }

        return PlayCards(*cards)
    }
}
