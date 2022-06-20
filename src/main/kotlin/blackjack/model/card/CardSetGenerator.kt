package blackjack.model.card

object CardSetGenerator {

    fun generateOneCardSet(symbols: List<CardSymbol>, numbers: List<CardNumber>): Cards {
        val cards = mutableListOf<Card>()
        symbols.forEach { symbol -> cards.addAll(generateCards(symbol, numbers)) }
        return Cards(cards)
    }

    private fun generateCards(symbol: CardSymbol, numbers: List<CardNumber>) =
        numbers.map { number -> Card(symbol, number) }
}
