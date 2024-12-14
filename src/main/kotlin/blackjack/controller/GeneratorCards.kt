package blackjack.controller

import blackjack.domain.BlackJackCard
import blackjack.domain.CardInfo
import blackjack.domain.CardType
import java.util.LinkedList

class GeneratorCards {
    private fun shuffle(cards: List<BlackJackCard>): LinkedList<BlackJackCard> {
        val inputCards = cards.toList()
        return LinkedList(inputCards.shuffled().toList())
    }

    private fun generateCards(): List<BlackJackCard> {
        val cards = mutableListOf<BlackJackCard>()
        CardType.entries.map { type ->
            CardInfo.entries.map { info ->
                cards.add(BlackJackCard(type, info))
            }
        }

        return cards.toList()
    }

    fun generateCardDeck(): LinkedList<BlackJackCard> {
        return shuffle(generateCards())
    }
}
