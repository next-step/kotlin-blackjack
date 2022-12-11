package model

open class Player(
    val name: String,
    val cards: Cards = Cards()
) {
    var bet: Int = 0

    fun hit(card: Card) = cards.add(card)

    fun bettingReward(dealer: Dealer): Int =
        BlackJackBettingRewardCalculator(player = this, dealer = dealer).reward()
}
