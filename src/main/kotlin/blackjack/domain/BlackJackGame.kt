package blackjack.domain

class BlackJackGame(
    playerNames: List<String>,
    private val playingCards: PlayingCardPack,
    private val playerInteraction: PlayerInteraction
) {

    private val players = playerNames.map { Player(it, POINT_RULE) }

    init {
        this.playingCards.shuffle()
    }

    fun play(): GameResult {
        initDraw()
        players.forEach { playerInteraction(it) }
        return GameResult(this.players.map { Pair(it, it.cards.point()) })
    }

    private fun initDraw() {
        repeat(INIT_CARD_COUNT) {
            players.forEach { it.receive(playingCards.pick()) }
        }
        playerInteraction.showInitDraw(INIT_CARD_COUNT, players)
    }

    private fun playerInteraction(player: Player) {
        val point = player.cards.point()
        if (point < POINT_RULE.winningPoint && playerInteraction.askHit(player)) {
            player.receive(playingCards.pick())
            playerInteraction.showPlayer(player)
            playerInteraction(player)
        }
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
        private val POINT_RULE = BlackJackRule()
    }
}
