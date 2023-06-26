package blackjack.view

import blackjack.domain.GameCardsSet
import blackjack.domain.Player
import blackjack.domain.Players

class InputView {
    fun inputPlayers(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        val gameCardsSet = GameCardsSet()
        val names = readln().split(',')
        val players = names.map { Player(it, gameCardsSet) }

        return Players(players)
    }
}
