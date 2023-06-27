package blackjack.domain

class BlackJackGame(
    playerNames: List<String>,
    private val playingCards: PlayingCardPack = PlayingCardPackFactory.createPack()
) {

    private val players = playerNames.map { Player(it, POINT_RULE) }

    init {
        this.playingCards.shuffle()
    }

    fun initDraw(): GameInit {
        repeat(INIT_CARD_COUNT) {
            players.forEach { it.receive(playingCards.pick()) }
        }
        return GameInit(INIT_CARD_COUNT, players)
    }

    fun play(askHit: (Player) -> Boolean, showPlayer: (Player) -> Unit): GameResult {
        players.forEach { playerInteraction(it, askHit, showPlayer) }
        return GameResult(this.players.map { Pair(it, it.cards.point()) })
    }

    private fun playerInteraction(player: Player, askHit: (Player) -> Boolean, showPlayer: (Player) -> Unit) {
        val point = player.cards.point()
        if (point < POINT_RULE.winningPoint && askHit(player)) {
            player.receive(playingCards.pick())
            showPlayer(player)
            playerInteraction(player, askHit, showPlayer)
        }
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
        private val POINT_RULE = BlackJackRule()
    }
}
