package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.player.Gambler

object GamblersReader {

    fun read(): List<Gambler> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val gamblerNames = readln().split(',').map { it.trim() }

        check(gamblerNames.isNotEmpty()) { "플레이어를 입력해주세요." }

        return gamblerNames.map { Gambler(it) }
    }
}
