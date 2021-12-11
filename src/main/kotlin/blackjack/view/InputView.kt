package blackjack.view

import blackjack.exception.BizException
import blackjack.exception.enums.InputError

object InputView {

    private const val INPUT_PLAYER = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)"
    private const val HITS_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예 y, 아니오는 n)"

    fun setPlayer(): List<String> {
        println(INPUT_PLAYER)
        return readLine()?.split(",") ?: throw BizException(InputError.NOT_INPUT)
    }

    fun hitsAndStay(name: String): Boolean {
        println("$name $HITS_MESSAGE")
        return ("y" == readLine())
    }
}
