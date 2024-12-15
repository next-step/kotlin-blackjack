package blackjack.domain

import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol
import blackjack.entity.CardInfo

object Deal {
    private val cards = Card.entries.toMutableList()
    private val symbols = CardSymbol.entries.toMutableList()
    private val gaveCards = mutableSetOf<Pair<CardSymbol, Card>>()

    fun giveCards(
        count: Int,
        isFaceUp: Boolean = true,
    ): List<CardInfo> {
        val remainingCards = symbols.size * cards.size - gaveCards.size
        require(count <= remainingCards) { "Requested cards exceed the available deck size. Remaining: $remainingCards" }

        return List(count) {
            val cardPair =
                generateSequence {
                    val shuffledSymbols = symbols.shuffled()
                    val shuffledCards = cards.shuffled()
                    shuffledSymbols.first() to shuffledCards.first()
                }.first { it !in gaveCards }

            gaveCards.add(cardPair)
            CardInfo(mapOf(cardPair.first to cardPair.second), isFaceUp)
        }
    }

    fun resetDeck() {
        gaveCards.clear()
    }
}
