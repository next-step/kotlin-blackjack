package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.card.Hand
import blackjack.domain.gameresult.GameResultSummary
import blackjack.domain.player.Player

object GameResultPrinter {

    fun dealer(dealer: Dealer) = println("딜러카드: ${cardOfHandMessage(dealer.hand)} - 결과: ${scoreMessage(dealer.hand)}")
    fun player(player: Player) =
        println("${playerNameMessage(player)}카드: ${cardOfHandMessage(player.hand)} - 결과: ${scoreMessage(player.hand)}")

    fun summary(gameResultSummary: GameResultSummary) {
        println("## 최종 승패")
        println("딜러: ${gameResultSummary.dealerProfit()}")
        gameResultSummary.playerResults
            .forEach { println("${playerNameMessage(it.player)}: ${it.profit}") }
    }

    private fun cardOfHandMessage(hand: Hand): String = hand.getCards().joinToString(
        separator = ", ",
        transform = { "${it.cardNumber.displayName}${it.cardSuit.displayName}" }
    )

    private fun scoreMessage(hand: Hand): String {
        if (hand.bust()) return "BUST"
        if (hand.blackjack()) return "BLACKJACK"
        return hand.total().toString()
    }

    private fun playerNameMessage(player: Player): String = player.name.value
}
