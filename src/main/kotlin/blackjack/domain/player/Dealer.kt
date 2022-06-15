package blackjack.domain.player

class Dealer : Player("딜러") {
    override val isFirstCardHidden: Boolean = true
}
