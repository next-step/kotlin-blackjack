package blackjack.view.input

import blackjack.domain.player.Name
import blackjack.domain.player.PlayerBetAmount

class GamePlayerBetAmountInputView(name: Name) : InputView<PlayerBetAmount>() {
    override val message: String = "${name.value}의 베팅 금액은?"
    override val value: PlayerBetAmount

    init {
        renderMessage()
        value = readValue()
    }

    override fun readValue(): PlayerBetAmount {
        return PlayerBetAmount(readln().toLong())
    }
}
