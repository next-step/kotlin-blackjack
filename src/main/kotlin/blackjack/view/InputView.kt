package blackjack.view

import blackjack.domain.Player

fun getPlayerNames(): List<String> {
    println("게임에 참여할 사람의 이름을 입력하세요.")
    return getConsoleLine().split(",")
}

fun getBettingMoney(name: String): Int {
    println("${name}의 배팅 금액은?")
    return getConsoleLine().toInt()
}

fun askHitFromPlayer(player: Player): Boolean {
    println("${player.info.name}은(는) 한 장의 카드를 더 받겠습니까? (y or n)")
    return when (getConsoleLine()) {
        "y" -> true
        "n" -> false
        else -> {
            println("y 또는 n만 입력 가능합니다. 다시 입력해 주세요.")
            println()
            askHitFromPlayer(player)
        }
    }
}

private fun getConsoleLine() = readLine()
    ?: throw IllegalArgumentException("콘솔 값을 가져올 수 없습니다.")
