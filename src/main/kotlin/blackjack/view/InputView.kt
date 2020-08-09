package blackjack.view

fun getPlayerNames(): List<String> {
    println("게임에 참여할 사람의 이름을 입력하세요.")
    return readLine()!!.split(",")
}

fun askFitFromPlayer(name: String): Boolean {
    println("${name}은(는) 한 장의 카드를 더 받겠습니까? (y or n)")
    return when (readLine()!!) {
        "y" -> true
        "n" -> false
        else -> throw IllegalArgumentException("y 또는 n만 입력 가능합니다.")
    }
}
