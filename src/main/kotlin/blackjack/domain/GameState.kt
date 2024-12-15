package blackjack.domain

data class GameState(
    private val hands: Hands = Hands(),
    private var bet: Bet = Bet(0),
) {
    val score: Int
        get() = hands.calculateTotalValue()

    val handSize: Int
        get() = hands.size

    val cards: String
        get() = hands.toString()

    val betValue: Int
        get() = bet.value

    fun addCard(card: Card) {
        hands.add(card)
    }

    fun blackjackBet(): Bet {
        this.bet = bet.blackjack()
        return bet
    }

    fun applyBet(bet: Bet): Bet {
        this.bet = this.bet.apply(bet)
        return this.bet
    }

    fun loseBet(): Bet {
        this.bet = bet.lose()
        return bet
    }
}
