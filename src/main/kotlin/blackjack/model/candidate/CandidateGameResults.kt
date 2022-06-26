package blackjack.model.candidate

class CandidateGameResults private constructor(
    val results: List<CandidateGameResult>
) {

    val candidates
        get() = results.map { it.candidate }

    companion object {
        fun from(candidates: Candidates): CandidateGameResults {
            val dealer = candidates.candidates
                .firstOrNull { it.isDealer }

            require(dealer != null) { "딜러가 존재하지 않습니다." }

            val players = candidates.candidates
                .filter { it != dealer }

            val dealerGameResults = players.map { CandidateGameResult.of(dealer, it) }
            val dealerWinCount = dealerGameResults.sumOf { it.winCount }
            val dealerLostCount = dealerGameResults.sumOf { it.lostCount }

            return listOf(CandidateGameResult(dealer, dealerWinCount, dealerLostCount))
                .plus(players.map { CandidateGameResult.of(it, dealer) })
                .let(::CandidateGameResults)
        }
    }
}
