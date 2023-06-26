package blackjack.domain

import blackjack.domain.Card.Companion.ALL_CARDS
import kotlin.random.Random

object GameCardsSet {
    private val drawnCards: MutableSet<Card> = mutableSetOf()
    private val remainingCards: List<Card>
        get() = ALL_CARDS.values.filter { it !in drawnCards }

    fun drawRandomCard(): Card {
        if (remainingCards.isEmpty()) {
            throw IllegalStateException("남은 카드가 없습니다.")
        }

        val randomIndex = Random.nextInt(remainingCards.size)
        val drawnCard = remainingCards[randomIndex]
        drawnCards.add(drawnCard)
        return drawnCard
    }
}
