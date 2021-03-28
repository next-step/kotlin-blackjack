package view

import blackjack.domain.GameResult
import blackjack.domain.card.Card
import blackjack.domain.player.User
import blackjack.domain.player.UserInfo
import blackjack.domain.player.UserName

class ConsoleOutput {
    fun printUserNameInputMessage() {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
    }

    fun printFirstDrawMessage(info: UserInfo) {
        val builder = StringBuilder().apply {
            val playerNames = info.players.users.joinToString(", ") { it.userName.name }
            append("\n${info.dealer.userName.name}와 ${playerNames}에게 2장의 카드를 나누었습니다.\n")

            append(
                "${info.dealer.userName.name}카드: ${
                info.dealer.hands.cards.drop(1).joinToString(", ") { showCard(it) }
                }\n"
            )
            info.players.users.forEach {
                append("${showHands(it)}\n")
            }
        }
        println(builder)
    }

    private fun showHands(user: User): String {
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

    fun printCardAndScore(info: UserInfo) {
        println()
        println("${showHands(info.dealer)} - 결과: ${info.dealer.calculateScore().score}")
        info.players.users.forEach { println("${showHands(it)} - 결과: ${it.calculateScore().score}") }
    }

    fun printDealerDrawingMessage() {
        println("딜러는 16 이하라 한 장의 카드를 더 받았습니다.")
    }

    fun printGameRecord(result: GameResult) {
        val builder = StringBuilder().apply {
            append("\n## 최종 수익\n")
            append("딜러: ${result.dealer}\n")
            result.players.forEach { append("${it.key.userName.name}: ${it.value.money}\n") }
        }
        println(builder)
    }

    fun printBettingMoneyInputMessage(name: UserName) {
        println("${name.name}의 배팅 금액은?")
    }
}
