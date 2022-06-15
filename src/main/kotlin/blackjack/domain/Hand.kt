package blackjack.domain

data class Hand(val cards: List<Card>, val bet: Bet = Bet.EMPTY_BET) {

    val count: Int
        get() = cards.size

    val betAmount: Int
        get() = bet.amount

    fun addCard(newCard: Card) = Hand(cards + newCard, bet)
}
