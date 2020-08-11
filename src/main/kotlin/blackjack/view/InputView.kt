package blackjack.view

import blackjack.domain.Player

fun getPlayerNames(): List<String> {
    println("게임에 참여할 사람의 이름을 입력하세요.")
    return getConsoleLine().split(",")
}

fun askFitFromPlayer(player: Player): Boolean {
    println("${player.name}은(는) 한 장의 카드를 더 받겠습니까? (y or n)")
    return when (getConsoleLine()) {
        "y" -> true
        "n" -> false
        else -> {
            println("y 또는 n만 입력 가능합니다. 다시 입력해 주세요.")
            println()
            askFitFromPlayer(player)
        }
    }
}

private fun getConsoleLine() = readLine()
    ?: throw IllegalArgumentException("콘솔 값을 가져올 수 없습니다.")
