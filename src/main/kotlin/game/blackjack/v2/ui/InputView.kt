package game.blackjack.v2.ui

import game.blackjack.v2.domain.participant.Player

object InputView {
    private const val PARTICIPANTS_PROMPT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val DRAW_ADDITIONAL_CARD_PROMPT = "%s은(는) 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun getParticipantNames(): List<String> {
        println(PARTICIPANTS_PROMPT)
        return readln().split(",")
    }

    fun isDrawAdditionalCard(player: Player): Boolean {
        println(String.format(DRAW_ADDITIONAL_CARD_PROMPT, player.name))
        return readln().trim().lowercase() == "y"
    }
}
