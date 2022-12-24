package blackjack.domain.card

import blackjack.domain.Blackjack
import kotlin.math.abs

object CardCalculator {
    const val BLACKJACK_BEST_SCORE: Int = Blackjack.BLACKJACK_BEST_SCORE

    fun score(cards: Cards): Int =
        cards.map { it.candidateScores }
            .cartesianProduct()
            .map { it.sum() }
            .filter { it <= BLACKJACK_BEST_SCORE }
            .minWithOrNull(compareBy { BLACKJACK_BEST_SCORE - it })
            ?: proximateScore(cards)


    private fun proximateScore(cards: Cards): Int =
        cards.map { it.candidateScores }
            .cartesianProduct()
            .map { it.sum() }
            .minWithOrNull(compareBy { abs(BLACKJACK_BEST_SCORE - it) })
            ?: (BLACKJACK_BEST_SCORE + 1)
}

fun <T> Collection<Iterable<T>>.cartesianProduct(): List<List<T>> =
    if (isEmpty()) emptyList()
    else drop(1)
        .fold(first().map(::listOf)) { acc, iterable ->
            acc.flatMap { list ->
                iterable.map(list::plus)
            }
        }
