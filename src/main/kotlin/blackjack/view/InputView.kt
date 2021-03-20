package blackjack.view

import blackjack.domain.Player

object InputView {
    private const val INPUT_PLAYER_NAMES_COMMENT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_DEAL_QUESTION_COMMENT = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    private const val ACCEPT_DEAL_ANSWER = "y"
    private const val REJECT_DEAL_ANSWER = "n"

    fun enterPlayerNames(): String {
        println(INPUT_PLAYER_NAMES_COMMENT)
        return readLine() ?: throw IllegalArgumentException()
    }

    fun enterDealAnswer(player: Player): String {
        println("${player.name}$INPUT_DEAL_QUESTION_COMMENT")
        val dealAnswer = readLine() ?: throw IllegalArgumentException()
        require(dealAnswer == ACCEPT_DEAL_ANSWER || dealAnswer == REJECT_DEAL_ANSWER) {
            "카드 수락 여부는, '$ACCEPT_DEAL_ANSWER' 혹은 '$REJECT_DEAL_ANSWER' 으로만 입력 하여야 합니다."
        }
        return dealAnswer
    }
}
