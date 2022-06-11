package blackjack.ui.input

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
}
