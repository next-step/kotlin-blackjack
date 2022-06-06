package blackjack.ui

object InputView {

    private const val MIN_PLAYER_NUMBER = 2
    private const val MAX_PLAYER_NUMBER = 6

    tailrec fun readPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().split(",")
        return when (names.size in MIN_PLAYER_NUMBER..MAX_PLAYER_NUMBER) {
            true -> names
            false -> {
                println("블랙잭을 진행하기 위한 적정 인원은 $MIN_PLAYER_NUMBER ~ $MAX_PLAYER_NUMBER 명 입니다")
                return readPlayerNames()
            }
        }
    }

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
