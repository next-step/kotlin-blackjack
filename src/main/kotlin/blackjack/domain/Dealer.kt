package blackjack.domain

class Dealer(card1: Card, card2: Card): Participant("딜러", card1, card2) {

    override fun isObtainable(): Boolean {
        return sumOfCards() < 17
    }

    override fun openCards(): List<Card> {
        return listOf(hands.first())
    }

    fun compareWith(vararg players: Player): Map<String, CompareResult> {
        return players.associate { it.name to compareWith(it) }
    }

    private fun compareWith(player: Player): CompareResult {
        if (isMoreThanBlackjack()) {
            return CompareResult.DEALER_LOSE
        }
        if (player.isMoreThanBlackjack()) {
            return CompareResult.DEALER_WIN
        }
        return compareBySumOfCards(player)
    }

    private fun compareBySumOfCards(player: Player): CompareResult {
        if (sumOfCards() == player.sumOfCards()) {
            return CompareResult.DRAW
        }
        if (sumOfCards() < player.sumOfCards()) {
            return CompareResult.DEALER_LOSE
        }
        return CompareResult.DEALER_WIN
    }
}
