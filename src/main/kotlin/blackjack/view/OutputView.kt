package blackjack.view

import blackjack.domain.player.Player

object OutputView {
    fun printCardState(players: List<Player>) {
        println("${players.joinToString(separator = ", ") { it.name.value }}에게 2장의 카드를 나누었습니다.")
        players.forEach { printCurrentCardState(it) }
    }

    fun printCurrentCardState(player: Player) {
        println("${player.name}카드: ${playerCardsToString(player)}")
    }

    fun printResult(player: Player) {
        println("${player.name}카드: ${playerCardsToString(player)} - 결과: ${player.state.hands.score().value}")
    }

    private fun playerCardsToString(player: Player): String {
        return player.state.getCards().joinToString(separator = ", ") {
                card -> "${card.character.displayName}${card.shape.displayName}"
        }
    }
}
