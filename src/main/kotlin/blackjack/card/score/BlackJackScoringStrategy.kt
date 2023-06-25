package blackjack.card.score

import blackjack.card.Card

class BlackJackScoringStrategy {
    fun score(cards: List<Card>): Int {
        return pickBestResult(generateAvailableResults(cards))
    }

    private fun generateAvailableResults(cards: List<Card>): Results {
        return cards.fold(Results(listOf(Result(0)))) { acc, card ->
            updateListWithCard(card, acc)
        }
    }

    private fun pickBestResult(results: Results): Int {
        return results.minBy { IDEAL_SCORE - it.value }.value
    }

    private fun updateListWithCard(card: Card, currList: Results): Results {
        val pack = card.cardSignaturePack
        val numbers =
            pack.numberSignature?.values
                ?: throw IllegalArgumentException("블랙잭에서 스코어링 할 수 있는 카드가 아닙니다")
        val resultList = numbers.map { number -> currList.map { result -> Result(number + result.value) } }
            .asSequence()
            .flatten()
            .filter { it.value <= IDEAL_SCORE }
            .toMutableList()
        return Results(resultList)
    }

    companion object {
        private const val IDEAL_SCORE = 21
    }

    private class Result(val value: Int)
    private class Results(val results: List<Result>) : Iterable<Result> by results
}
