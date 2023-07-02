package blackjack.domain

class Dealer(
    private val gameCardsSet: GameCardsSet,
) : Player("딜러", gameCardsSet) {
    companion object {
        const val DEALER_UNDER_NUMBER: Int = 16
    }
}
