package blackjack.view

object ResultView {
    private const val GIVE_CARD = "에게 2장의 카드를 나누었습니다."
    private const val ASK_MORE_CARD = ": 한장의 카드를 더 받겠습니까?"

    private const val ERR_INVALID_NAME = "1명 이상의 이름을 입력해주세요."

    fun printInvalidName() {
        println(ERR_INVALID_NAME)
    }
}
