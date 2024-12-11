package blackjack.controller

import blackjack.domain.BlackJackCard
import java.util.LinkedList

class GeneratorCards {
    private fun shuffle(cards: List<BlackJackCard>): LinkedList<BlackJackCard> {
        val inputCards = cards.toList()
        return LinkedList(inputCards.shuffled().toList())
    }

    private fun generateCards(): List<BlackJackCard> {
        val cards = mutableListOf<BlackJackCard>()
        (1..52).map {
            cards.add(BlackJackCard(it))
        }

        return cards.toList()
    }

    fun generateCardDeck(): LinkedList<BlackJackCard> {
        return shuffle(generateCards())
    }
}
