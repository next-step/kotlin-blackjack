package view

import blackjack.domain.card.Card
import blackjack.domain.player.Players

class ConsoleOutput {
    fun printUserNameInputMessage() {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
    }

    fun printFirstDrawMessage(players: Players) {
        val builder = StringBuilder().apply {
            val names = players.players.joinToString(",") { it.playerName.name }
            append("${names}에게 2장의 카드를 나누었습니다.\n")

            players.players.forEach {
                append("${it.playerName.name}카드 : ${it.hands.cards.joinToString(", ") { showCard(it) }}\n")
            }
        }
        println(builder)
    }

    private fun showCard(card: Card): String {
        return "${card.symbol.alias}${card.suit.korean}"
    }
}
