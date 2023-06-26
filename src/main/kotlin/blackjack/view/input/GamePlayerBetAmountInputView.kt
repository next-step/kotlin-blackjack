package blackjack.view.input

import blackjack.domain.player.Name

class GamePlayerBetAmountInputView(name: Name) : InputView<Long>() {
    override val message: String = "${name.value}의 베팅 금액은?"
    override val value: Long

    init {
        renderMessage()
        value = readValue()
    }

    override fun readValue(): Long {
        return readln().toLong()
    }
}
