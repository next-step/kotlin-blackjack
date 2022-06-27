package blackjack.view

import blackjack.model.candidate.CandidateName
import blackjack.model.candidate.Player

object ConsoleInputView : InputView {
    private const val PLAYER_NAME_DELIMITER = ","

    override fun printPlayerNamesInputMessage() = println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

    override fun inputPlayerNames() = readln().split(PLAYER_NAME_DELIMITER)
        .map { Player.from(it, 1) }

    override fun printNeedMoreCardAskMessage(name: CandidateName) =
        println("${name.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

    override fun inputWhetherNeedMoreCard() = readln()
}
