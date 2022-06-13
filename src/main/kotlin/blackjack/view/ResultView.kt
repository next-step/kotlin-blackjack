package blackjack.view

import blackjack.domain.GameRule.INIT_DRAW_CARD_COUNT
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.asText

object ResultView {

    fun printDistributeCard(players: Players) {
        println("${players.namesAsText()}에게 ${INIT_DRAW_CARD_COUNT}를 나누어주었습니다.")
    }

    fun printResult(player: Player) {
        println("${player.name} : ${player.cards.asText()} : ${player.score()}")
    }
}
