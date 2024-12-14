package blackjack.entity

class Dealer(val name: String) {
    private val blackJack: BlackJack = BlackJack()

    fun getDealerBlackJack(): BlackJack {
        return blackJack
    }
}
