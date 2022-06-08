package blackjack.view

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

object ResultView {
    fun printResult(dealer: Dealer, players: List<Player>, result: blackjack.domain.game.Result) {
        println("\n${ParticipantView.parseParticipantInfoToString(dealer)} - 결과: ${dealer.score()}")
        players.forEach { player -> println("${ParticipantView.parseParticipantInfoToString(player)} - 결과: ${player.score()}") }

        println("\n## 최종 승패")
        result.scoreMap.forEach { (participant, score) ->
            println("${participant.name}: ${score.win}승 ${score.lose}패")
        }
    }
}
