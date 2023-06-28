package blackjack.dealer

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardSuit

object CardDeck {

    private val cardDeck: MutableList<Card> = CardSuit.values().flatMap { pattern ->
        CardNumber.values().map { number -> Card(number, pattern) }
    }.toMutableList()

    fun draw(): Card {
        val randomIndex = (0 until cardDeck.size).random()
        return cardDeck.removeAt(randomIndex)
    }

    val size: Int
        get() = cardDeck.size
}
