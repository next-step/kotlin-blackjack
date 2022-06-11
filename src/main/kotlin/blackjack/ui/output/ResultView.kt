package blackjack.ui.output

import blackjack.domain.participant.Match
import blackjack.domain.participant.Participant
import blackjack.domain.result.BlackJackResult
import blackjack.domain.result.DealerResult
import blackjack.domain.result.PlayerResult

object ResultView {

    fun showResultHand(participants: List<Participant>) {
        println()
        participants.forEach {
            println(it.display())
        }
    }

    private fun Participant.display(): String {
        return "${name}카드: $hand - 결과: ${point().value}"
    }

    fun showBlackJackResult(blackJackResult: BlackJackResult) {
        println("## 최종 승패")
        showDealerResult(blackJackResult.dealerResult)
        showPlayersResults(blackJackResult.playerResults)
    }

    private fun showDealerResult(dealerResult: DealerResult) {
        with(dealerResult) {
            println("딜러: ${win}승 ${draw}무 ${lose}패")
        }
    }

    private fun showPlayersResults(playerResults: List<PlayerResult>) {
        playerResults.forEach {
            println(it.display())
        }
    }

    private fun PlayerResult.display(): String {
        return when (this.match) {
            Match.WIN -> "$playerName: 승"
            Match.DRAW -> "$playerName: 무"
            Match.LOSE -> "$playerName: 패"
        }
    }
}
