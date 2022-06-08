package blackjack.view

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

object ResultView {
    fun printResult(dealer: Dealer, players: List<Player>, result: blackjack.domain.game.Result) {
        println("\n${ParticipantView.parseParticipantInfoToString(dealer)} - 결과: ${dealer.score()}")
        players.forEach { player -> println("${ParticipantView.parseParticipantInfoToString(player)} - 결과: ${player.score()}") }

        println("\n## 최종 승패")
        result.score.forEach { (participant, winCount) ->
            println("${participant.name}: $winCount")
        }
    }
}
