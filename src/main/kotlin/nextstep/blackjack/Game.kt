package nextstep.blackjack

private const val INIT_DRAW_CARD_COUNT = 2

class Game(val players: List<Player>) {
    private val cardPack: CardPack = CardPack()

    init {
        players.forEach { player ->
            repeat(INIT_DRAW_CARD_COUNT) { player.hit(cardPack.draw()) }
        }
    }

    fun drawTo(player: Player) {
        players.first { it.name == player.name }.also { it.hit(cardPack.draw()) }
    }
}
