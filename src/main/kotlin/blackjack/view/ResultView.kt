package blackjack.view

object ResultView {

    enum class Message(val context: String) {
        REQUEST_PLAYERS("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
        REQUEST_HIT("는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"),
        BUST(" 님의 카드 숫자의 총 수가 21을 초과하여 다음 Player 턴으로 넘어 갑니다.");
    }

    fun printMessage(message: Message) = println(message.context)

    fun printMessage(prefix: String, message: Message) = println("${prefix}${message.context}")

    fun printHit(payers: List<String>) {
        val playNames = payers.joinToString(". ")

        println("${playNames}에게 2장의 나누었습니다.")
    }

    fun printState(player: String, cards: List<String>) {
        val cardString: String = cards.joinToString(", ")

        println("${player}카드: $cardString")
    }

    fun printResult(player: String, cards: List<String>, point: Int) {
        val cardString: String = cards.joinToString(", ")

        println("${player}카드: $cardString - 결과: $point")
    }

    fun newLine() = println()
}
