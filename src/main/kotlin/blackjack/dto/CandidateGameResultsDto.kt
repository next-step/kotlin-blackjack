package blackjack.dto

import blackjack.model.candidate.Candidates
import blackjack.model.candidate.Dealer
import blackjack.model.candidate.Player

class CandidateGameResultsDto private constructor(
    val results: List<CandidateGameResultDto>
) {

    val candidates
        get() = results.map { it.candidate }

    companion object {
        fun from(candidates: Candidates): CandidateGameResultsDto {
            val dealer = candidates.candidates
                .firstOrNull { it.isDealer }

            require(dealer != null) { "딜러가 존재하지 않습니다." }

            val playerGameResults = candidates.candidates
                .filter { it != dealer }
                .map { CandidateGameResultDto.of(it as Player, dealer as Dealer) }

            val dealerProfit = -playerGameResults.sumOf { it.profit }
            val dealerGameResult = CandidateGameResultDto(dealer, dealerProfit)

            return (listOf(dealerGameResult) + playerGameResults)
                .let(::CandidateGameResultsDto)
        }
    }
}
