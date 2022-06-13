package blackjack.view

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.game.Result

object ResultView {
    fun printResult(dealer: Dealer, players: List<Player>, result: Result) {
        println("\n${ParticipantView.parseParticipantInfoToString(dealer)} - 결과: ${dealer.score()}")
        players.forEach { player -> println("${ParticipantView.parseParticipantInfoToString(player)} - 결과: ${player.score()}") }

        println("\n## 최종 수익")
        result.totalEarningsByParticipantName.forEach { (name, totalEarnings) ->
            println("$name: $totalEarnings")
        }
    }
}
