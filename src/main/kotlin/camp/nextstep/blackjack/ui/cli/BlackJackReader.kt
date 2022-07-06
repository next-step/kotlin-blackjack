package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.game.Action
import camp.nextstep.blackjack.player.Gambler

object BlackJackReader {

    fun readGamblerAction(gamblerName: String): Action {
        println("${gamblerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val wantHit = requireNotNull(readLine()) { "y / n 을 입력해주세요." } == "y"
        return if (wantHit) Action.HIT else Action.STAY
    }

    fun readGamblers(): List<Gambler> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val gamblerNames = readln().split(',').map { it.trim() }

        check(gamblerNames.isNotEmpty()) { "플레이어를 입력해주세요." }

        return gamblerNames.map { Gambler(it) }
    }
}
