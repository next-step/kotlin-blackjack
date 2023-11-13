package blackjack.ui

object OutputView {
    fun printEnterParticipantsName() {
        println(ENTER_PARTICIPANTS_NAME)
    }

    fun printParticipantsName(names: String) {
        val participants = names.split(NAME_DELIMITERS)
        val participantsCount = participants.count()

        val divideCardString = PRINT_DIVIDE_CARD_TO_PARTICIPANTS.format(names, participantsCount)
        println(divideCardString)
    }

    private const val ENTER_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val NAME_DELIMITERS = ", "
    private const val PRINT_DIVIDE_CARD_TO_PARTICIPANTS = "%s 에게 %d장의 카드를 나누었습니다."
}
