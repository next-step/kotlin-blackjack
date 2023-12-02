package view

import player.Player
import player.PlayerGroup

object OutputView {

    fun showGameStart(playerGroup: PlayerGroup) {
        val sb = StringBuilder()
        playerGroup.playerList.forEach {
            sb.append("${it.name}, ")
        }
        sb.deleteCharAt(sb.lastIndex - 1)
        sb.append("에게 2장의 나누었습니다.")
        println(sb.toString())
        playerGroup.playerList.forEach { showPlayingCard(it) }
        println()
    }

    fun showPlayingCard(player: Player) {
        println("${player.name}카드: ${player.playerDeck}")
    }

    fun showGameEnd(playerGroup: PlayerGroup) {
        println()
        playerGroup.playerList.forEach { showPlayerCardWithPoint(it) }
    }

    private fun showPlayerCardWithPoint(player: Player) {
        val totalPoint = player.getResultPoint()
        println("${player.name}카드: ${player.playerDeck} - 결과: $totalPoint")
    }
}
