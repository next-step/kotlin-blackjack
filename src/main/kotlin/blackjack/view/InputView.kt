package blackjack.view

import blackjack.domain.player.Player

fun inputPlayerNames(): List<String> {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    return (readLine() ?: throw IllegalArgumentException())
        .split(",")
}

fun inputAnswer(player: Player): Boolean {
    println("${player.name.value}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    val answer = readLine() ?: throw IllegalArgumentException()
    return when (answer) {
        "y" -> true
        "n" -> false
        else -> inputAnswer(player)
    }
}
