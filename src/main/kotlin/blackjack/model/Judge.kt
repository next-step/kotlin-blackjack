package blackjack.model

object Judge {
    fun resolve(dealer: CardHolder.GameDealer, player: CardHolder.Player) {
        when {
            player.isBust -> winOrLose(dealer, player)
            dealer.isBust -> winOrLose(player, dealer)
            player.cardHand.totalScore > dealer.cardHand.totalScore -> winOrLose(player, dealer)
            player.cardHand.totalScore < dealer.cardHand.totalScore -> winOrLose(dealer, player)
            else -> draw(dealer, player)
        }
    }

    private fun winOrLose(winner: CardHolder, loser: CardHolder) {
        winner.winLoseDraw.win++
        loser.winLoseDraw.lose++
    }

    private fun draw(player1: CardHolder, player2: CardHolder) {
        player1.winLoseDraw.draw++
        player2.winLoseDraw.draw++
    }
}
