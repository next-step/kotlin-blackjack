package blackjack.view

import blackjack.domain.Player

fun getPlayerNames(): List<String> {
    println("게임에 참여할 사람의 이름을 입력하세요.")
    return getConsoleLine().split(",")
}

// y 누른후 보유 카드 보여주려햇는데 어떻게 해야할지 감이 안온다.
// 메소드파라미터로 넣기에는 너무 많아지기도 하고.. 그래서 편법으로 물어보기 전에 한번 보여준다.
// 근데 busted 되면 무슨카드를 뽑아서 죽엇는지 알길이 없다...

fun askHitFromPlayer(player: Player): Boolean {
    println("${player.name}은(는) 한 장의 카드를 더 받겠습니까? (y or n)")
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
