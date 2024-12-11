package blackjack.view

import blackjack.domain.Player

object ResultView {
    fun printFinalScores(player: Player) {
        println(
            buildString {
                playerNameWithCards(player)
                append(" - 결과: ${player.getCardSum()}")
            },
        )
    }

    fun printPlayerInitMessage(names: List<String>) {
        val namesToString = names.joinToString(separator = ", ")
        println("$namesToString 에게 2장의 카드를 나누었습니다.")
    }

    fun printPlayerNameWithCards(player: Player) {
        println(playerNameWithCards(player))
    }

    private fun playerNameWithCards(player: Player): String {
        return "${player.name} 카드: ${player.cards.map { "${it.rank.rankName}${it.suit.koreanName}" }}"
    }
}
