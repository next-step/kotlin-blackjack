package blackjack.domain

import kotlin.random.Random

object GameCardsSet {
    private val allCards: MutableList<Card> = generateCards()
    private val drawnCards: MutableSet<Card> = mutableSetOf()

    val remainingCards: List<Card>
        get() = allCards.filter { it !in drawnCards }

    fun drawRandomCard(): Card? {
        if (remainingCards.isEmpty()) {
            return null
        }

        val randomIndex = Random.nextInt(remainingCards.size)
        val drawnCard = remainingCards[randomIndex]
        drawnCards.add(drawnCard)
        return drawnCard
    }

    private fun generateCards(): MutableList<Card> {
        val generatedCards = mutableListOf<Card>()

        Suits.values().forEach { suit ->
            generateCardsForSuit(suit, generatedCards)
        }

        return generatedCards
    }

    private fun generateCardsForSuit(suit: Suits, generatedCards: MutableList<Card>) {
        Ranks.values().forEach { rank ->
            val card = Card.createCard(rank, suit)
            generatedCards.add(card)
        }
    }
}
