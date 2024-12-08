package blackjack.domain

import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol

object Deal {
    private val cards = Card.entries.toMutableList()
    private val symbols = CardSymbol.entries.toMutableList()
    private val gaveCards = mutableSetOf<Pair<CardSymbol, Card>>()

    fun giveCard(): Map<CardSymbol, Card> {
        val shuffledSymbols = symbols.shuffled()
        val shuffledCards = cards.shuffled()
        val cardPair = shuffledSymbols.first() to shuffledCards.first()
        return if (cardPair !in gaveCards) {
            gaveCards.add(cardPair)
            mapOf(cardPair.first to cardPair.second)
        } else {
            giveCard()
        }
    }

    fun resetDeck() {
        gaveCards.clear()
    }
}
