package blackjack.domain

data class GameState(
    private val hands: Hands = Hands(),
    private var bet: Bet = Bet.zero(),
) {
    val score: Int
        get() = hands.calculateTotalValue()

    val handSize: Int
        get() = hands.size

    val cards: String
        get() = hands.toString()

    val betValue: Int
        get() = bet.value

    val isBust: Boolean
        get() = hands.isBust

    val isBlackjack: Boolean
        get() = hands.isBlackjack

    fun addCard(card: Card) {
        hands.add(card)
    }

    fun blackjackBet(): Bet {
        this.bet = bet.blackjack()
        return bet
    }

    fun applyBet(bet: Bet): Bet {
        this.bet += bet
        return this.bet
    }

    fun loseBet(): Bet {
        val bet = this.bet
        this.bet = this.bet.lose()
        return bet
    }
}
