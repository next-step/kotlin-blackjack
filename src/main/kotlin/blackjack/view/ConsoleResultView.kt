package blackjack.view

import blackjack.dto.CandidateGameResultsDto
import blackjack.model.candidate.Candidate
import blackjack.model.candidate.CandidateName
import blackjack.model.candidate.Candidates
import blackjack.model.candidate.Dealer.Companion.BOUNDARY_SCORE_FOR_RECEIVING_MORE_CARD

object ConsoleResultView : ResultView {
    private const val CARD_SEPARATOR = ", "

    override fun printCandidatesCardStatus(candidates: Candidates) {
        println()

        with(StringBuilder()) {
            candidates.candidates.map { candidate -> this.append("${candidate.name}에게 ${candidate.cardSize}장을 ") }
            this.append("나누었습니다.")
            println(this)
        }

        candidates.candidates.map { printCandidateCardStatus(it) }

        println()
    }

    override fun printCandidateCardStatus(candidate: Candidate) = println(candidateCardStatus(candidate))

    override fun printCardGameResult(results: CandidateGameResultsDto) {
        println()

        results.candidates.forEach { candidate ->
            val score1 = candidate.sumOfCardScore.score1
            val score2 = candidate.sumOfCardScore.score2
            println("${candidateCardStatus(candidate)} - 결과: ${candidateCardScore(score1, score2)}")
        }

        println("\n## 최종 수익")
        results.results.forEach { gameResult ->
            println("${gameResult.candidateName}: ${gameResult.profit}")
        }
    }

    override fun printDealerReceiveMoreCardMessage(dealerName: CandidateName) =
        println("\n${dealerName.name}는 점수가 $BOUNDARY_SCORE_FOR_RECEIVING_MORE_CARD 이하라 한 장의 카드를 더 받았습니다.")

    private fun candidateCardStatus(candidate: Candidate): String {
        val cards = candidate.cards.cards
            .joinToString(CARD_SEPARATOR) { card -> "${card.numberMark}${card.symbol}" }
        return "${candidate.name}카드: $cards"
    }

    private fun candidateCardScore(score1: Int, score2: Int): String {
        if (score1 == score2) {
            return "$score1"
        }
        return "$score1 or $score2"
    }
}
