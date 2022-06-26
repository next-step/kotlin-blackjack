package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.game.Action
import camp.nextstep.blackjack.player.Gambler

object GamblerActionReader {

    fun read(gambler: Gambler): Action {
        println("${gambler.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val wantHit = requireNotNull(readLine()) { "y / n 을 입력해주세요." } == "y"
        return if (wantHit) Action.HIT else Action.STAY
    }
}
