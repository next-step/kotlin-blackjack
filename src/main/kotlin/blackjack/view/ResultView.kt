package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

private const val SEPARATOR = ", "

object ResultView {

    fun printDrawResults(players: Players) {
        println("${players.items.map { it.name }.joinToString { it }}에게 2장의 나누었습니다.")

        players.items.forEach {
            println(it.cardScoreDescription())
        }
    }

    fun printCardScoreDescription(player: Player) {
        println(player.cardScoreDescription())
    }

    fun printResults(player: Player) {
        println("${player.cardScoreDescription()} - 결과: ${player.resultScore()}")
    }

    private fun Player.cardScoreDescription() = "${this.name}카드: ${this.cardsDescription()}"
    private fun Player.cardsDescription() =
        this.cardElements.map { c -> c.number.desc + c.sharp.desc }.joinToString { it }
}
