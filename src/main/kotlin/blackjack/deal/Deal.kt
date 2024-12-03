package blackjack.deal

import blackjack.enums.Card
import blackjack.enums.CardSymbol

object Deal {
    private val cards = Card.entries.toMutableList()
    private val symbols = CardSymbol.entries.toMutableList()

    fun giveCard(): Map<CardSymbol, Card> {
        val shuffledSymbols = symbols.shuffled()
        val shuffledCards = cards.shuffled()
        return mapOf(shuffledSymbols.first() to shuffledCards.first())
    }
}
