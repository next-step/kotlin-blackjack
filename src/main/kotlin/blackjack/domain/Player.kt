package blackjack.domain

class Player(val name: String) {
    val hands = mutableListOf<PokerCard>()
}
