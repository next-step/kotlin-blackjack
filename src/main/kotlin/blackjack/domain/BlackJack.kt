package blackjack.domain

object BlackJack {
    fun checkBurst(player: Player): Boolean {
        return player.getScore() > 21
    }
}
