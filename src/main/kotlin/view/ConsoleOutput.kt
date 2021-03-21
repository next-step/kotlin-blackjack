package view

import blackjack.domain.UserInfo
import blackjack.domain.card.Card
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.User

class ConsoleOutput {
    fun printUserNameInputMessage() {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
    }

    fun printFirstDrawMessage(info: UserInfo) {
        val builder = StringBuilder().apply {
            val playerNames = info.players.players.joinToString(", ") { it.userName.name }
            append("${info.dealer.userName.name}와 ${playerNames}에게 2장의 카드를 나누었습니다.\n")

            append("${showHands(info.dealer)}\n")
            info.players.players.forEach {
                append("${showHands(it)}\n")
            }
        }
        println(builder)
    }

    private fun showHands(user: User): String {
        if (user is Dealer) {
            return "${user.userName.name}카드: ${user.hands.cards.drop(1).joinToString(", ") { showCard(it) }}"
        }
        return "${user.userName.name}카드: ${user.hands.cards.joinToString(", ") { showCard(it) }}"
    }

    private fun showCard(card: Card): String {
        return "${card.symbol.alias}${card.suit.korean}"
    }

    fun printDecideDrawingMessage(user: User) {
        println("${user.userName.name}는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
    }

    fun printHandsStatus(user: User) {
        println(showHands(user))
    }

    fun printGameResult(players: Players) {
        println()
        players.players.forEach { println("${showHands(it)} - 결과: ${it.calculateScore()}") }
    }
}
