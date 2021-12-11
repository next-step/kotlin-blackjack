package blackjack.view

import blackjack.exception.BizException
import blackjack.exception.enums.InputError

object InputView {

    private const val INPUT_PLAYER = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)"

    fun setPlayer(): String {
        println(INPUT_PLAYER)
        return readLine() ?: throw BizException(InputError.NOT_INPUT)
    }
}
