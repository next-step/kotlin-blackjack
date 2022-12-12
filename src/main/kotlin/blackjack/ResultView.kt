package blackjack

import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.model.Card

object ResultView {
    fun printInitialCards(players: Players) {
        println("${players.value.joinToString { it.name }}에게 ${INITIAL_CARDS_COUNT}장의 카드를 나누었습니다.")
        players.value.map { println(getPlayerInfo(it)) }
        println()
    }

    fun printPlayerCards(player: Player) = println(getPlayerInfo(player))

    fun printResult(players: Players) {
        players.value.map { println("${getPlayerInfo(it)} - 결과: ${it.sumCards()}") }
    }

    private fun getPlayerInfo(player: Player) = "${player.name}카드: ${player.cards.value.joinToString { "${it.type.value}${it.shape.text}" }}"
}
