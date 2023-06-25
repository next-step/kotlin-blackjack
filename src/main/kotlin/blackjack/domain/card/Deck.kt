package blackjack.domain.card

import kotlin.random.Random

class Deck {
    private val pickedCards: MutableSet<Card> = mutableSetOf()
    private val remainedCards: List<Card>
        get() = Card.CACHE.values.filter {
            it !in pickedCards
        }

    fun draw(): Card? {
        if (remainedCards.isEmpty()) {
            return null
        }
        return getRandomCard()
    }

    private fun getRandomCard(): Card {
        val randomIndex = Random.nextInt(remainedCards.size)
        val drawnCard = remainedCards[randomIndex]
        pickedCards.add(drawnCard)
        return drawnCard
    }
}
