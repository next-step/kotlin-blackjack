package blackjack.view

import blackjack.domain.BlackJackResult
import blackjack.domain.Hands
import blackjack.domain.Player

class BlackJackView {
    fun askPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readLine()!!.split(",").map { it.trim() }
    }

    fun startGame(players: List<Player>) {
        println("${players.map { it.name }.joinToStrings()}에게 2장의 카드를 나누었습니다.")
        players.forEach {
            printPlayer(it)
        }
        println()
    }

    fun askPlayerToHit(player: Player): Boolean {
        println("${player.name.`은는`()} 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readLine()!!.trim().toLowerCase()
        return when (answer) {
            "y" -> true
            "n" -> false
            else -> {
                println("잘못 입력하셨습니다.")
                askPlayerToHit(player)
            }
        }
    }

    fun printPlayer(player: Player) {
        println(printPlayerHands(player))
    }

    private fun printPlayerHands(player: Player): String {
        return "${player.name}카드: ${player.hands.strings()}"
    }

    fun printResult(result: BlackJackResult) {
        println()
        result.players.forEach {
            println("${printPlayerHands(it)} - 결과: ${it.hands.sum}")
        }
    }

    private fun String.`은는`(): String = if (VOWELS.any { endsWith(it, true) }) "${this}는" else "${this}은"

    fun Hands.strings() = cards.map { it.cardName }.joinToStrings()
    private fun List<String>.joinToStrings() = joinToString(prefix = "", postfix = "", separator = ", ") { it }

    companion object {
        private val VOWELS = setOf(
            'a',
            'e',
            'i',
            'o',
            'u'
        )
    }
}
