package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

object OutputView {

    fun outputPlayers(players: Players) {
        println("${players.joinToString { it.name }}에게 ${players.size}장을 나누었습니다.")
        players.forEach(::showPlayerCard)
    }

    fun showPlayerCard(player: Player) {
        println("${player.name}카드: ${player.playerCards.joinToString { it.toString() }}")
    }

    fun showResultScore(player: Player, totalScore: Int) {
        println("${player.name}카드: ${player.playerCards.joinToString { it.toString() }} - 결과: $totalScore")
    }
}
