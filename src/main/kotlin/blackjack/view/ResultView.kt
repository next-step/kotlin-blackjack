package blackjack.view

import blackjack.domain.Player

object ResultView {
    fun firstDealCard(players: List<Player>) {
        println("${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")

    }

    fun showPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards.cards}")
    }

    fun printResult(player: Player) {
        print("\n${player.name}카드: ${player.cards.cards} - 결과: ${player.getScore()}")
    }


    // player가 가지고 잇는 card 리스트를
    private fun Player.cardToString() : String{
        return ""
    }
}
