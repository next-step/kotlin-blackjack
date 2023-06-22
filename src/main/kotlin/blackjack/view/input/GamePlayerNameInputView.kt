package blackjack.view.input

import blackjack.domain.player.Name

class GamePlayerNameInputView : InputView<List<Name>>() {
    override val message: String = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    override val value: List<Name>

    init {
        this.renderMessage()
        value = readValue()
    }

    override fun readValue(): List<Name> {
        return readln().split(DELIMITER).map { Name(it) }
    }

    companion object {
        const val DELIMITER = ","
    }
}
