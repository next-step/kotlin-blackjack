package blackjack.ui

object OutputView {
    fun printEnterParticipantsName() {
        println(ENTER_PARTICIPANTS_NAME)
    }

    fun printParticipantsName(names: String) {
        val divideCardString = PRINT_DIVIDE_CARD_TO_PARTICIPANTS.format(names)
        println(divideCardString)
    }

    private const val ENTER_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val PRINT_DIVIDE_CARD_TO_PARTICIPANTS = "%s 에게 각각 2장의 카드를 나누었습니다."
}
