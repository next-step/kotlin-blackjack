package blackjack.entity

class Dealer(val name: String, defaultCards: List<CardInfo>) {
    val hand: PlayerHand = PlayerHand(defaultCards)
}
