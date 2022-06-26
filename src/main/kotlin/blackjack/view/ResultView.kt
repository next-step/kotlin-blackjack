package blackjack.view

import blackjack.model.candidate.Candidate
import blackjack.model.candidate.CandidateGameResults
import blackjack.model.candidate.CandidateName
import blackjack.model.candidate.Candidates

interface ResultView {
    fun printCandidatesCardStatus(candidates: Candidates)

    fun printCandidateCardStatus(candidate: Candidate)

    fun printCardGameResult(results: CandidateGameResults)

    fun printDealerReceiveMoreCardMessage(dealerName: CandidateName)
}
