package blackjack.view

import betting.Bet
import betting.BetResult
import blackjack.participant.Participant
import blackjack.player.Player

object InputView {
    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readlnOrNull()?.split(",")?.map { it.trim() } ?: emptyList()
    }

    fun isHitCard(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return "y" == readlnOrNull()
    }

    fun inputBettingAmount(player: Participant<*>): BetResult {
        println("${player.name}의 베팅 금액은?")
        return BetResult(bet = Bet(readlnOrNull()?.toLongOrNull() ?: 0L))
    }
}
