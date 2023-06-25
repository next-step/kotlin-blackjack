package blackjack.domain

private const val START_CARD_COUNT = 2

class BlackJack(val players: List<Player>) {
    fun start() {
        for (i in 0 until START_CARD_COUNT) players.forEach { it.draw() }
    }
}