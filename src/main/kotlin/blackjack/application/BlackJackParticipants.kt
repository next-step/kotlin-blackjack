package blackjack.application

import blackjack.application.dto.BlackJackStatuses
import blackjack.application.dto.BlackJackWinningResults
import blackjack.application.dto.BlackjackScores
import blackjack.domain.card.CardDeck
import blackjack.domain.participant.dealer.Dealer
import blackjack.domain.participant.dealer.DealerScoreStrategy
import blackjack.domain.participant.dealer.blackJackScore
import blackjack.domain.participant.dealer.status
import blackjack.domain.participant.dealer.winningResult
import blackjack.domain.participant.player.Player
import blackjack.domain.participant.player.PlayerScoreStrategy
import blackjack.domain.participant.player.Players
import blackjack.domain.participant.player.blackJackScores
import blackjack.domain.participant.player.statuses
import blackjack.domain.participant.player.winningResults
import blackjack.domain.participant.vo.Name

class BlackJackParticipants(
    private val dealer: Dealer,
    private val players: Players
) {

    val statuses: BlackJackStatuses
        get() = BlackJackStatuses(listOf(dealer.status()) + players.statuses())

    val scores: BlackjackScores
        get() = BlackjackScores(listOf(dealer.blackJackScore()) + players.blackJackScores())

    private val winningResults: BlackJackWinningResults
        get() = BlackJackWinningResults(listOf(dealer.winningResult()) + players.winningResults())

    val hasMorePlayablePlayer: Boolean
        get() = players.playable.isEmpty()

    val isDealerDrawMoreCard: Boolean
        get() = dealer.isDealerDrawMoreCard

    val names: List<Name>
        get() = listOf(dealer.name) + players.names

    val isDealerBust: Boolean
        get() = dealer.participantInformation.isBust()

    fun ready(cardDeck: CardDeck) {
        dealer.ready(cardDeck)
        players.ready(cardDeck)
    }

    fun hitPlayers(hitAction: (Player) -> Unit) {
        players.hit(hitAction)
    }

    fun hitDealer(cardDeck: CardDeck) {
        dealer.hit(cardDeck)
    }

    fun winningResults(): BlackJackWinningResults {
        dealer.score(DealerScoreStrategy(players))
        players.score(PlayerScoreStrategy(dealer))

        return winningResults
    }
}
