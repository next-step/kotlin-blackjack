package blackjack

data class Bat(private val player: CardPlayer, private var _money: Int = 0) {
    val money: Int
        get() = _money

    infix fun beats(dealer: Bat) {
        dealer._money -= _money
        _money *= 2
    }
}
