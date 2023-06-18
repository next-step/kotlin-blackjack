package blackjack.domain.game

import blackjack.domain.card.PlayingCards
import blackjack.domain.deck.Deck
import blackjack.domain.game.result.ParticipantPlayResult
import blackjack.domain.game.result.ParticipantPlayResults
import blackjack.domain.state.running.Hit
import blackjack.event.GameEvent
import blackjack.event.PlayerEvent
import blackjack.participant.Dealer
import blackjack.participant.ParticipantName
import blackjack.participant.Player
import blackjack.participant.Players

class BlackjackGame(
    playerNames: List<String>,
    private val deck: Deck = Deck(),
) {

    val players: Players = Players(
        players = playerNames.map(this::createPlayer),
    )

    private val dealer: Dealer = Dealer(
        state = Hit(playingCards = initialCard()),
    )

    private fun createPlayer(name: String) = Player(
        participantName = ParticipantName(name = name),
        state = Hit(
            playingCards = initialCard(),
        ),
    )

    private fun initialCard() = PlayingCards(
        cards = deck.multiDraw(
            count = INIT_HAND_COUNT,
        ).toMutableSet(),
    )

    fun start(gameEvent: GameEvent): ParticipantPlayResults {
        val playerPlayResults = players.map {
            playTurn(player = it, playerEvent = gameEvent.playerEvent)
        }

        val dealerPlayResult = dealer.play(dealerEvent = gameEvent.dealerEvent) {
            deck.draw()
        }

        return ParticipantPlayResults(
            dealerPlayResult = dealerPlayResult,
            playerPlayResults = playerPlayResults,
        )
    }

    private fun playTurn(player: Player, playerEvent: PlayerEvent): ParticipantPlayResult =
        player.play(playerEvent = playerEvent) {
            deck.draw()
        }

    companion object {
        const val INIT_HAND_COUNT: Int = 2
    }
}
