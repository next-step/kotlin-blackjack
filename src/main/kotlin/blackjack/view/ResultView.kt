package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.asText

object ResultView {

    fun printDistributeCard(cardCount: Int, players: Players) {
        println("${players.namesAsText()}에게 ${cardCount}를 나누어주었습니다.")
    }

    fun printResult(player: Player) {
        println("${player.name} : ${player.cards.asText()} : ${player.score()}")
    }
}
