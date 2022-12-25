package blackjack.domain.card

import java.util.LinkedList

object Deck {

    private val deck = LinkedList(
        CardNumber.values().flatMap { denomination ->
            CardShape.values().map { suit ->
                Card(denomination, suit)
            }
        }.shuffled()
    )

    fun draw(): Card {
        return deck.pop()
    }
}
