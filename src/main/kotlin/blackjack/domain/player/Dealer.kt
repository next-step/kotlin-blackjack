package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardsDeck

class Dealer(
    val dealer: Participant
) : BlackjackFunction by dealer {

    fun addCardWhenLessThanStandard(
        cardsDeck: CardsDeck,
    ): Card? {
        if (dealer.getCardSum() >= STANDARD) {
            return null
        }

        val card = cardsDeck.divide()
        addCard(card)

        return card
    }

    fun matchWhenFirstCardBlackjack(player: Player) {
        val isPlayerBlackjack = player.isBlackjack()
        val isMyBlackjack = isBlackjack()

        if (isPlayerBlackjack && !isMyBlackjack) {
            val revenue = player.betAmount / HALF
            lose(player, revenue)
        }

        if (!isPlayerBlackjack && isMyBlackjack) {
            win(player, player.betAmount)
        }
    }

    fun match(player: Player) {
        val playerCardSum = player.getCardSum()
        val myCardSum = getCardSum()
        if (isWin(myCardSum, playerCardSum)) {
            win(player, player.betAmount)
            return
        }

        if (isLose(myCardSum, playerCardSum)) {
            lose(player, player.betAmount)
            return
        }
    }

    private fun isWin(dealerCardSum: Int, playerCardSum: Int): Boolean {
        if (playerCardSum > DEADLINE) {
            return true
        }

        if (dealerCardSum in (playerCardSum + 1)..DEADLINE) {
            return true
        }

        return false
    }

    private fun isLose(dealerCardSum: Int, playCardSum: Int): Boolean {
        if (dealerCardSum > DEADLINE) {
            return true
        }

        if (playCardSum > dealerCardSum) {
            return true
        }

        return false
    }

    private fun win(player: Player, betAmount: Double) {
        addRevenue(betAmount)
        player.minusRevenue(betAmount)
    }

    private fun lose(player: Player, betAmount: Double) {
        minusRevenue(betAmount)
        player.addRevenue(betAmount)
    }

    companion object {
        private const val STANDARD = 17
        private const val DEADLINE = 21
        private const val HALF = 2
    }
}
