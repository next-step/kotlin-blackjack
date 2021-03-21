package blackjack.domain

internal class BlackJackGame {
    private val cardPack: CardPack = CardPack()

    fun startGame(players: List<Player>) {
        players.forEach {
            val player = it
            repeat(2) {
                player.acceptCard(cardPack.next())
            }
        }
    }

    fun hit(player: Player) {
        if (player.canHit()) {
            player.acceptCard(cardPack.next())
        }
    }

    companion object {
        const val MAX_SCORE = 21
    }
}
