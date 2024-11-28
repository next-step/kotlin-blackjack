package blackjack.view

import blackjack.domain.Player

object ResultView {

    fun printStartCards(players: List<Player>) {
        println(buildString {
            append(players.joinToString { it.name })
            append("에게 2장의 나누었습니다.\n")
            players.forEach { append("${getPlayerInformation(it)}\n") }
        })
    }

    fun printPlayerCard(player: Player) {
        println(getPlayerInformation(player))
    }

    fun printPlayerResult(players: List<Player>) {
        println(buildString {
            players.forEach { player ->
                append("${getPlayerInformation(player)} - 결과: ${player.cards.sum()}\n")
            }
        })
    }

    private fun getPlayerInformation(player: Player): String {
        return buildString {
            append(player.name)
            append("카드: ")
            append(player.cards.cards.joinToString { "${it.rank.displayName}${it.suit.displayName}" })
        }
    }

}
