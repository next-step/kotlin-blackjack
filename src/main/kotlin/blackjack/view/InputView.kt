package blackjack.view

import blackjack.domain.DealMachine
import blackjack.domain.Player

object InputView {
    private const val INPUT_PLAYER_NAMES_COMMENT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_DEAL_QUESTION_COMMENT = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun enterPlayerNames(): String {
        println(INPUT_PLAYER_NAMES_COMMENT )
        return readLine() ?: throw IllegalArgumentException()
    }

}