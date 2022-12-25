package blackjack.domain.card

import blackjack.domain.Blackjack
import kotlin.math.abs

class Cards(
    cards: List<Card>,
) : AbstractCards(cards.toMutableList()) {
    val score: Int
        get() = score()

    private fun score(): Int =
        cards.map { it.candidateScores }
            .cartesianProduct()
            .map { it.sum() }
            .filter { it <= Blackjack.BLACKJACK_BEST_SCORE }
            .minWithOrNull(compareBy { Blackjack.BLACKJACK_BEST_SCORE - it })
            ?: proximateScore()

    private fun proximateScore(): Int =
        cards.map { it.candidateScores }
            .cartesianProduct()
            .map { it.sum() }
            .minWithOrNull(compareBy { abs(Blackjack.BLACKJACK_BEST_SCORE - it) })
            ?: (Blackjack.BLACKJACK_BEST_SCORE + 1)

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean = score > Blackjack.BLACKJACK_BEST_SCORE

    fun isBlackjack(): Boolean = score == Blackjack.BLACKJACK_BEST_SCORE

    companion object {
        val ALL: Cards = Cards(Card.ALL_CARD_LIST)
    }
}

private fun <T> Collection<Iterable<T>>.cartesianProduct(): List<List<T>> =
    if (isEmpty()) emptyList()
    else drop(1)
        .fold(first().map(::listOf)) { acc, iterable ->
            acc.flatMap { list ->
                iterable.map(list::plus)
            }
        }
