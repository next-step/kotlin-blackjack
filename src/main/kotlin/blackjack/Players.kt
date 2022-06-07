package blackjack

class Players(val values: List<Player>) {
    fun findNotOver(): List<Player> {
        return values.filter { !it.gameOver }
    }
}
