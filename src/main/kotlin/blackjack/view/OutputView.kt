package blackjack.view

import blackjack.Player
import blackjack.card.Card

object OutputView {
    fun printInitialCardCasting(players: List<Player>, castedCard: Int) {
        val playerNames = players.joinToString(separator = ", ") { it.name }
        println("${playerNames}에게 ${castedCard}장을 나누었습니다.")
        players.forEach { printPlayerCards(it) }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${joinPlayerCardNames(player.cards)}")
    }

    fun printBlackJackResult(player: Player, result: Int) {
        println("${player.name}카드: ${joinPlayerCardNames(player.cards)} - 결과: $result")
    }

    private fun joinPlayerCardNames(cards: List<Card>): String {
        return cards.joinToString(separator = ", ") { card -> card.toString() }
    }
}
