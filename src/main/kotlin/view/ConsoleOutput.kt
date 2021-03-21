package view

import blackjack.domain.GameResult
import blackjack.domain.ResultType
import blackjack.domain.player.UserInfo
import blackjack.domain.card.Card
import blackjack.domain.player.User

class ConsoleOutput {
    fun printUserNameInputMessage() {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
    }

    fun printFirstDrawMessage(info: UserInfo) {
        val builder = StringBuilder().apply {
            val playerNames = info.players.users.joinToString(", ") { it.userName.name }
            append("${info.dealer.userName.name}와 ${playerNames}에게 2장의 카드를 나누었습니다.\n")

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

    fun printHandsStatus(info: UserInfo) {
        println(showHands(info.dealer))
        info.players.users.forEach { println(showHands(it)) }
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
            append("\n## 최종 승패\n")
            append("딜러: ${result.dealer[ResultType.WIN]}승 ${result.dealer[ResultType.DRAW]}무 ${result.dealer[ResultType.LOSE]}패\n")
            result.players.forEach { append("${it.key.userName.name}: ${it.value.symbol}\n") }
        }
        println(builder)
    }
}
