package blackjack

data class Bat(private val player: CardPlayer, private var _money: Int = 0) {
    val money: Int = _money

    infix fun beats(dealer: Bat) {
        _money *= 2
        dealer._money -= _money
    }
}
