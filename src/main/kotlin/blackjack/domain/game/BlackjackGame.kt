package blackjack.domain.game

import blackjack.domain.card.PlayingCards
import blackjack.domain.deck.Deck
import blackjack.domain.state.running.Hit
import blackjack.event.GameEvent
import blackjack.participant.Player
import blackjack.participant.player.PlayerName
import blackjack.participant.player.PlayerResult
import blackjack.participant.player.PlayerResults
import blackjack.participant.player.Players

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
        const val INIT_HAND_COUNT: Int = 2
    }
}
