package blackjack.entity

class Player(val name: String, initialCards: List<CardInfo>) {
    val hand: PlayerHand = PlayerHand(initialCards)
}
