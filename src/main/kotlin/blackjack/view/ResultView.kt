package blackjack.view

import blackjack.domain.InitDrawCard
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.asText

object ResultView {

    fun printDistributeCard(players: Players) {
        println("${players.namesAsText()}에게 ${InitDrawCard().count}를 나누어주었습니다.")
    }

    fun printResult(player: Player) {
        println("${player.name} : ${player.cards.asText()} : ${player.score()}")
    }
}
