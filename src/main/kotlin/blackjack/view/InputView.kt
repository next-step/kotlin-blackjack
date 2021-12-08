package blackjack.view

import blackjack.domain.Player

fun getPlayerNames(): List<String> =
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)").run {
        getStringData().split(",")
    }

private fun getStringData() = (readLine() ?: throw IllegalArgumentException())

fun getOrderFrom(player: Player): Boolean =
    println("${player}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)").run {
        getAnswerFromUser()
    }

fun askAceIsEleven(player: Player): Boolean =
    println("${player}께서 Ace를 가지고 계십니다. Ace를 11로 환산하시겠습니까?(예는 y, 아니오는 n)").run {
        getAnswerFromUser()
    }

private fun getAnswerFromUser() = when (getStringData()) {
    "y" -> true
    "n" -> false
    else -> throw IllegalArgumentException()
}
