package blackjack.ui

import blackjack.domain.Decision
import blackjack.domain.HitDecision
import blackjack.domain.StayDecision
import blackjack.domain.player.PlayerName

object DecisionReader {
    fun read(playerName: PlayerName): Decision {
        println("${playerName.value}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return Answer.valueOf(readln()).decision
    }
}

enum class Answer(val decision: Decision) {
    y(HitDecision),
    n(StayDecision),
}
