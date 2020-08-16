package blackjack.view

import blackjack.model.player.Player

object ResultView {
    private const val DEALER_MORE_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다."

    fun showPlayer(player: Player) {
        println("$player")
    }

    fun showPlayers(players: List<String>) {
        players.forEach(::println)
    }

    fun showResult(players: List<String>) {
        println("-- 게임 결과 -- ")
        players.forEach(::println)
    }

    fun showDealerMoreCard() {
        println(DEALER_MORE_CARD)
    }
}
