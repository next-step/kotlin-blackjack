package blackjack.view.input

import blackjack.ErrorCode
import blackjack.domain.player.Name

class GamePlayerReceiveInputView(name: Name) : InputView<GamePlayerReceiveInputResponse>() {
    override val message: String = "${name.value}는 한장의 카드를 더 받겠습니까? (예: y, 아니오: n)"
    override val value: GamePlayerReceiveInputResponse

    init {
        renderMessage()
        value = readValue()
    }

    override fun readValue(): GamePlayerReceiveInputResponse {
        return GamePlayerReceiveInputResponse(readln())
    }
}

class GamePlayerReceiveInputResponse(message: String) {
    val value: Boolean
    init {
        require(message == MESSAGE_YES || message == MESSAGE_NO) { ErrorCode.INVALID_PLAYER_RECEIVE_RESPONSE.message }
        value = message == MESSAGE_YES
    }

    companion object {
        const val MESSAGE_YES = "y"
        const val MESSAGE_NO = "n"
    }
}
