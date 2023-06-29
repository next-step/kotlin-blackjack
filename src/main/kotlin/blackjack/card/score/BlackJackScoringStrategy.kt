package blackjack.card.score

import blackjack.card.Card
import blackjack.card.deck.PlayerCardDeck
import kotlin.math.abs

class BlackJackScoringStrategy {
    fun score(deck: PlayerCardDeck): Int {
        return pickBestResult(generateAvailableResults(deck))
    }

    private fun generateAvailableResults(deck: PlayerCardDeck): ScoreResults {
        return deck.getCards().fold(ScoreResults(listOf(ScoreResult(0)))) { acc, card ->
            updateListWithCard(card, acc)
        }
    }

    private fun pickBestResult(results: ScoreResults): Int {
        return results.minBy { abs(IDEAL_SCORE - it.value) }.value
    }

    private fun updateListWithCard(card: Card, currList: ScoreResults): ScoreResults {
        val pack = card.cardSignaturePack
        val numbers =
            pack.numberSignature?.values
                ?: throw IllegalArgumentException("블랙잭에서 스코어링 할 수 있는 카드가 아닙니다")
        val resultList = numbers.map { number -> currList.map { result -> ScoreResult(number + result.value) } }
            .asSequence()
            .flatten()
            .toMutableList()
        return ScoreResults(resultList)
    }

    companion object {
        private const val IDEAL_SCORE = 21
    }

    private class ScoreResult(val value: Int)
    private class ScoreResults(val results: List<ScoreResult>) : Iterable<ScoreResult> by results
}
