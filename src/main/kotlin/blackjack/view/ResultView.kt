package blackjack.view

import blackjack.player.Player
import blackjack.player.Players

object ResultView {
    fun printPlayersName(players: Players) {
        println("${players.players.joinToString { it.name }}에게 ${players.players.size}장을 나누었습니다.")
    }

    fun printPlayersCardStatus(players: Players) {
        players.players.forEach { player ->
            printPlayerCard(player)
        }
        println()
    }

    fun printPlayersCardStatusAndSum(players: Players) {
        println()
        players.players.forEach { player ->
            printPlayerCard(player, sum = player.hand.sum())
        }
    }

    fun printPlayerCard(
        player: Player,
        sum: Int? = null,
    ) {
        println(generateCardListString(player, sum))
    }

    private fun generateCardListString(
        player: Player,
        sum: Int? = null,
    ) = "${player.name}카드: ${player.hand.cards.joinToString(
        ", ",
    ) { "${it.rank.value}${it.suit.description}" }} ${sum?.convertToResultString() ?: ""}"

    private fun Int.convertToResultString() = "- 결과: $this"

    fun printWinner(players: Players) {
        println("승자는 ${players.getWinner()?.let {"${it.name}입니다."} ?: "없습니다."}")
    }
}
