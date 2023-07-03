package view

import game.BlackJackResult
import player.Player

fun outputPlayerView(players: List<Player>) {
    println("${players.parsePlayersNames()} 에게 2장의 나누었습니다.")
    players.forEach {
        it.printNameAndCard()
    }
}

fun outputResult(blackJackResult: BlackJackResult) {
    println()
    blackJackResult.players.forEach {
        printResult(it, blackJackResult.calculateTotalScore(it))
    }
}

private fun printResult(player: Player, totalScore: Int) {
    println(player.parseNameAndCards() + " - 결과: $totalScore")
}

private const val SEPARATOR = ", "

private fun List<Player>.parsePlayersNames(): String = this.joinToString(SEPARATOR) { it.name.value }

fun Player.printNameAndCard() {
    println(parseNameAndCards())
}

private fun Player.parseNameAndCards(): String =
    "${name.value}카드: ${hand.cards.values.joinToString(SEPARATOR) { parseCard(it) } }"
