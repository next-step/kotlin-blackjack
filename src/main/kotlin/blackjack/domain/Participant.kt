package blackjack.domain

sealed class Participant(private val hand: Hand, private var money: Money = Money.ZERO) {
    val bettingMoney: Money
        get() = money
    val isBust: Boolean
        get() = hand.isBust
    val totalCards: Deck
        get() = Deck(hand.totalCards)
    val bustGap: Int
        get() = hand.bustGap()

    fun score(): Score {
        return hand.score
    }

    fun receive(cards: Deck) {
        hand.add(cards.values())
    }

    fun betting(other: Money) {
        this.money = this.money.plus(other)
    }

    fun evenMoney(): Money {
        return money.evenMoney()
    }

    fun isBlackJack(): Boolean {
        return hand.isBlackJack()
    }

    fun profit(other: Money): Money {
        return other - money
    }
}
