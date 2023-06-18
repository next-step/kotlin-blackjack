package blackjack.domain.game

import blackjack.domain.card.PlayingCards
import blackjack.domain.deck.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.PlayerResult
import blackjack.domain.player.PlayerResults
import blackjack.domain.player.Players
import blackjack.domain.state.running.Hit
import blackjack.event.GameEvent

class BlackjackGame(
    playerNames: List<String>,
    private val deck: Deck = Deck(),
) {

    val players: Players = Players(
        players = playerNames.map(this::createPlayer),
    )

    private fun createPlayer(name: String) = Player(
        playerName = PlayerName(name = name),
        state = Hit(
            playingCards = initialCard(),
        ),
    )

    private fun initialCard() = PlayingCards(
        cards = deck.multiDraw(
            count = INIT_HAND_COUNT,
        ).toMutableSet(),
    )

    fun start(gameEvent: GameEvent): PlayerResults = players.map {
        playTurn(player = it, gameEvent = gameEvent)
    }.run(::PlayerResults)

    private fun playTurn(player: Player, gameEvent: GameEvent): PlayerResult = player.play(gameEvent = gameEvent) {
        deck.draw()
    }

    companion object {
        const val BUST_SCORE: Int = 21
        const val INIT_HAND_COUNT: Int = 2
    }
}
