package presentation

import domain.Player

object ResultView {
    fun printInitialState(players: List<Player>) {
        println("${players.names().joinToString(", ")}에게 2장의 카드를 나누었습니다.")
        players.print()
    }

    fun printPlayerState(player: Player) {
        player.print()
    }

    fun printResult(players: List<Player>) {
        players.forEach {
            it.printWithResult()
        }
    }

    private fun List<Player>.names(): List<String> {
        return map { it.name }
    }

    private fun List<Player>.print() {
        forEach {
            it.print()
        }
        println()
    }

    private fun Player.print() {
        println("$name 카드: ${cards.current().joinToString(", ")}")
    }

    private fun Player.printWithResult() {
        println("$name 카드: ${cards.current().joinToString(", ")} - 결과: ${result()}")
    }
}
