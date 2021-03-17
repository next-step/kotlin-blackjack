package view

import blackjack.domain.card.Card
import blackjack.domain.player.Player
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
                append("${showHands(it)}\n")
            }
        }
        println(builder)
    }

    private fun showHands(player: Player): String {
        return "${player.playerName.name}카드 : ${player.hands.cards.joinToString(", ") { showCard(it) }}"
    }

    private fun showCard(card: Card): String {
        return "${card.symbol.alias}${card.suit.korean}"
    }

    fun printDecideDrawingMessage(player: Player) {
        println("${player.playerName.name}는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
    }

    fun printHandsStatus(player: Player) {
        println(showHands(player))
    }
}
