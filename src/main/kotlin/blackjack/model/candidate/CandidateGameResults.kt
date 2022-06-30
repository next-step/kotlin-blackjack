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

            val playerGameResults = candidates.candidates
                .filter { it != dealer }
                .map { CandidateGameResult.of(it as Player, dealer as Dealer) }

            val dealerProfit = -playerGameResults.sumOf { it.profit }
            val dealerGameResult = CandidateGameResult(dealer, dealerProfit)

            return (listOf(dealerGameResult) + playerGameResults)
                .let(::CandidateGameResults)
        }
    }
}
