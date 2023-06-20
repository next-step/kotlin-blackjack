package blackjack.domain.game

import blackjack.domain.bet.Bet
import blackjack.domain.card.PlayingCards
import blackjack.domain.deck.Deck
import blackjack.domain.game.result.ParticipantPlayResult
import blackjack.domain.game.result.ParticipantPlayResults
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.ParticipantName
import blackjack.domain.participant.Player
import blackjack.domain.participant.PlayerInfo
import blackjack.domain.participant.Players
import blackjack.domain.state.running.Hit
import blackjack.event.GameEvent
import blackjack.event.PlayerEvent

class BlackjackGame(
    playerInfos: List<PlayerInfo>,
    private val deck: Deck = Deck(),
) {

    val players: Players = playerInfos.map {
        createPlayer(name = it.name, betAmount = it.betAmount)
    }.run(::Players)

    val dealer: Dealer = Dealer(
        state = Hit(playingCards = initialCard()),
    )

    private fun createPlayer(name: String, betAmount: Double) = Player(
        participantName = ParticipantName(name = name),
        bet = Bet(amount = betAmount),
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
