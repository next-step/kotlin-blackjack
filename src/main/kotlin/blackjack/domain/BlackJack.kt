package blackjack.domain

private const val START_CARD_COUNT = 2

const val BLACKJACK_MAX_SCORE = 21

object BlackJack {
    fun start(players: List<Player>) {
        for (i in 0 until START_CARD_COUNT) players.forEach { it.draw() }
    }

    fun canPlay(player: Player): Boolean {
        return player.cards.score() <= BLACKJACK_MAX_SCORE
    }
}