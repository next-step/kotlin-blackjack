package blackjack.view

import blackjack.player.Player
import blackjack.player.Players
import blackjack.view.output.Output

class OutputView(private val output: Output) {
    fun start(players: Players) {
        output.print("${players.names()}에게 2장을 나누었습니다.")
    }

    fun showAllPlayersCard(players: Players) {
        output.print(players.players.joinToString("\n") { playerMessage(it) })
    }

    fun showPlayerCard(player: Player) {
        output.print(playerMessage(player))
    }

    private fun playerMessage(player: Player) = "${player.playerName}카드: ${player.cardNames()}"

    fun end(players: Players) {
        output.print(players.players.joinToString("\n") { "${playerMessage(it)} - 결과: ${it.sumPoint()}" })
    }
}

fun Player.cardNames(): String {
    return this.cards().joinToString(", ") { it.fullName() }
}

fun Players.names(): String {
    return this.players.joinToString(", ") { it.playerName }
}
