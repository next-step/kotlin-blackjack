package blackjack.view

import blackjack.domain.Participants
import blackjack.domain.asText

object ResultView {

    fun printDistributeCard(cardCount: Int, players: Participants) {
        println("${players.namesAsText()}에게 ${cardCount}를 나누어주었습니다.")
    }

    fun printResult(participants: Participants) {
        participants.participants.forEach {
            println("${it.name} : ${it.cards.asText()} : ${it.score().number}")
        }

        println("## 최종 승패")

        val dealer = participants.dealer

        participants.player.map { player ->
            println("${player.name} : ${player.result(dealer).label}")
            dealer.result(player).label
        }.groupingBy { it }.eachCount().also {
            println("${dealer.name} : $it")
        }
    }
}
