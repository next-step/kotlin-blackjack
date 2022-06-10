package balckjac.view

import balckjac.domain.GameRule.INIT_DRAW_CARD_COUNT
import balckjac.domain.Player
import balckjac.domain.Players
import balckjac.domain.ScoreCalculator
import balckjac.domain.asText

object ResultView {

    fun printDistributeCard(players: Players) {
        println("${players.namesAsText()}에게 ${INIT_DRAW_CARD_COUNT}를 나누어주었습니다.")
    }

    fun printResult(player: Player) {
        println("${player.cards.asText()} : ${ScoreCalculator.calculate(player.cards)}")
    }
}
