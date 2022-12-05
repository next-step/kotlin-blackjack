package blackjack.view

import blackjack.domain.Player

private const val SEPARATOR = ", "

object ResultView {

    fun printDrawResults(players: List<Player>) {
        println("${players.map { it.name }.joinToString { it }}에게 2장의 나누었습니다.")

        players.forEach {
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
        this.cards.items.map { c -> c.number.desc + c.sharp.desc }.joinToString { it }
}
