package view

import blackjack.domain.card.Card
import blackjack.domain.player.Player

class ConsoleOutput {
    fun printUserNameInputMessage() {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
    }

    fun printFirstDrawMessage(players: List<Player>) {
        val builder = StringBuilder().apply {
            val names = players.joinToString(",") { it.playerName.name }
            append("${names}에게 2장의 카드를 나누었습니다.\n")

            players.forEach {
                append("${it.playerName.name}카드 : ${it.hands.joinToString(", ") { showCard(it) }}\n")
            }
        }
        println(builder)
    }

    private fun showCard(card: Card): String {
        return "${card.symbol.symbol}${card.suit.korean}"
    }
}
