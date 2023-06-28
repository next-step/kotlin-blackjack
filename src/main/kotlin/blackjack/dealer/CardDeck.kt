package blackjack.dealer

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardSuit
import kotlin.random.Random

object CardDeck {

    private val cardDeck: MutableList<Card> = CardSuit.values().flatMap { pattern ->
        CardNumber.values().map { number -> Card(number, pattern) }
    }.toMutableList()

    fun draw(): Card {
        val randomIndex = Random.nextInt(cardDeck.size)
        return cardDeck.removeAt(randomIndex)
    }

    val size: Int
        get() = cardDeck.size
}
