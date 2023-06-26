package blackjack.view

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participants
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerImpl

object OutputView {
    fun printGameStart(names: List<String>, initialDraw: Int) {
        println("\n딜러와 ${names.joinToString(", ")}에게 ${initialDraw}장을 나누었습니다.")
    }

    fun printPlayerCard(players: Participants) {
        players.players.forEach {
            printPlayerCard(it)
        }
        println()
    }

    fun printDealerCard(dealer: Dealer) {
        println("${dealer.name}카드: ${dealer.cardHold.cards[0].rank.mark}${dealer.cardHold.cards[0].shape.mark}")
    }

    fun printDealerGetAdditionalCard() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }

    fun printPlayerCard(player: PlayerImpl) {
        println("${player.name}카드: ${player.cardHold.cards.joinToString(", ") { it.rank.mark + it.shape.mark } }")
    }

    fun printPlayerResult(players: Participants) {
        println()
        players.players.forEach {
            printPlayerResult(it)
        }
    }

    private fun printPlayerResult(player: Player) {
        println("${player.name}카드: ${player.cardHold.cards.joinToString(", ") { it.rank.mark + it.shape.mark } } - 결과: ${player.getPoints()}")
    }
}
