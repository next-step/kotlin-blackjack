package blackjack.ui.input

object ParticipantView {
    tailrec fun askHit(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln()) {
            "y" -> true
            "n" -> false
            else -> {
                println("y 또는 n을 입력해 주세요")
                askHit(playerName)
            }
        }
    }
}
