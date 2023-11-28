package blackjack.domain

class Dealer(
    cardDeck: CardDeck
): Participant("딜러", cardDeck) {
    override val openedCards = listOf(hands.first())

    override fun isObtainable(): Boolean {
        return sumOfCards() < 17
    }

    fun compareWith(player: Player): CompareResult {
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
