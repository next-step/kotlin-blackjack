package blackjack.entity

class Game(val player: String) {
    private val blackJack: BlackJack = BlackJack()

    fun getPlayerBlackJack(): BlackJack {
        return blackJack
    }
}
