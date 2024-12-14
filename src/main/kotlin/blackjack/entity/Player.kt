package blackjack.entity

class Player(val name: String) {
    private val blackJack: BlackJack = BlackJack()

    fun getPlayerBlackJack(): BlackJack {
        return blackJack
    }
}
