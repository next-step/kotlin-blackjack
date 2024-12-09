package blackjack.domain

import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol

object Deal {
    private val cards = Card.entries.toMutableList()
    private val symbols = CardSymbol.entries.toMutableList()

    private val gaveCards = mutableSetOf<Pair<CardSymbol, Card>>()

    fun giveCards(count: Int): MutableList<MutableMap<CardSymbol, Card>> {
        val remainingCards = symbols.size * cards.size - gaveCards.size
        require(count < remainingCards) { "Requested cards exceed the available deck size. Remaining: $remainingCards" }

        val cardsToGive = mutableListOf<MutableMap<CardSymbol, Card>>()

        repeat(count) {
            val cardPair =
                generateSequence {
                    val shuffledSymbols = symbols.shuffled()
                    val shuffledCards = cards.shuffled()
                    shuffledSymbols.first() to shuffledCards.first()
                }.first { it !in gaveCards }

            gaveCards.add(cardPair)
            cardsToGive.add(mutableMapOf(cardPair.first to cardPair.second))
        }

        return cardsToGive
    }

    fun resetDeck() {
        gaveCards.clear()
    }
}
