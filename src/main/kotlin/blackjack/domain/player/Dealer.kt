package blackjack.domain.player

class Dealer : Player("딜러") {
    val shouldDrawCard: Boolean
        get() = cards.total.value <= 16
}
