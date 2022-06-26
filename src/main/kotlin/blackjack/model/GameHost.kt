package blackjack.model

import blackjack.model.candidate.Candidate
import blackjack.model.candidate.Candidates
import blackjack.model.card.CardNumber
import blackjack.model.card.CardScore
import blackjack.model.card.CardSetGenerator
import blackjack.model.card.CardSymbol
import blackjack.model.card.Cards

class GameHost(
    val cardSet: Cards = CardSetGenerator.generateOneCardSet(
        CardSymbol.values().toList(),
        CardNumber.values().toList()
    )
) {

    fun shuffleCards() = cardSet.shuffle()

    fun provideCardTo(candidates: Candidates, cardCount: Int = 1) {
        candidates.candidates.map { player ->
            repeat(cardCount) { provideOneCardTo(player) }
        }
    }

    fun provideOneCardTo(candidate: Candidate) {
        validateNotExceedMaxScore(candidate.sumOfCardScore)

        val drawnCard = cardSet.removeOne()
        candidate.receiveCard(drawnCard)
    }

    private fun validateNotExceedMaxScore(score: CardScore) =
        require(score.isOneOfScoreLessThan(MAX_SCORE)) { "카드 점수가 ${MAX_SCORE}점을 넘을 수 없습니다." }

    companion object {
        private const val MAX_SCORE = 21
    }
}
