package blackjack.view.input

import blackjack.domain.Player
import blackjack.domain.Players

class ConsoleInputView : InputView {
    override fun getPlayers(): Players {
        println(ENTER_PLAYERS_NAMES_MESSAGE)
        return Players.from(readLine() ?: "")
    }

    override fun askToReceiveAdditionalCardOrNot(player: Player): Boolean {
        println("${player.name}은(는) 한장의 카드를 더 받겠습니까?(예는 $YES, 아니오는 $NO)")
        return YES.equals(readLine() ?: NO, true)
    }

    companion object {
        private const val YES = "y"
        private const val NO = "n"
        private const val ENTER_PLAYERS_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        const val PLAYER_NAME_DELIMITER = ","
        const val NUMBER_OF_PLAYER_SHOULD_BE_LARGER_THAN_ZERO = "적어도 한명의 플레이어가 존재해야 합니다."
        const val WRONG_PLAYER_NAME_MESSAGE = "잘못된 플레이어 이름입니다.(1글자 이상, 공백문자 금지)"
    }
}
