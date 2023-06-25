package blackjack.domain

import blackjack.domain.BlackJackStatus.BLACK_JACK
import blackjack.domain.BlackJackStatus.BUST
import blackjack.domain.BlackJackStatus.STAY_OR_HIT

class BlackJackGame(playerNames: List<String>) {

    private val players = playerNames.map { Player(it) }
    private val playingCards: PlayingCardPack = PlayingCardPackFactory.createPack()

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
        return GameResult(this.players.map { Pair(it, BlackJackPointRule.point(it.cards)) })
    }

    private fun playerInteraction(player: Player, askHit: (Player) -> Boolean, showPlayer: (Player) -> Unit) {
        when (BlackJackPointRule.check(player.cards)) {
            BUST, BLACK_JACK -> {}
            STAY_OR_HIT -> {
                if (askHit(player)) {
                    player.receive(playingCards.pick())
                    showPlayer(player)
                    playerInteraction(player, askHit, showPlayer)
                }
            }
        }
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
