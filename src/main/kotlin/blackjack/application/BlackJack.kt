package blackjack.application

import blackjack.application.dto.BlackJackStatus
import blackjack.application.dto.BlackJackStatuses
import blackjack.application.dto.BlackJackWinningResults
import blackjack.application.dto.BlackjackScores
import blackjack.domain.card.CardDeck
import blackjack.domain.card.setupCardDeck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players
import blackjack.domain.participant.blackJackScore
import blackjack.domain.participant.blackJackScores
import blackjack.domain.participant.status
import blackjack.domain.participant.statuses
import blackjack.domain.participant.vo.Name
import blackjack.domain.participant.winningResult
import blackjack.domain.participant.winningResults

class BlackJack private constructor(
    private val dealer: Dealer,
    private val players: Players,
    private val cardDeck: CardDeck
) {
    val statuses: BlackJackStatuses
        get() = BlackJackStatuses(listOf(dealer.status()) + players.statuses())

    private val winningResults: BlackJackWinningResults
        get() = BlackJackWinningResults(listOf(dealer.winningResult()) + players.winningResults())

    val scores: BlackjackScores
        get() = BlackjackScores(listOf(dealer.blackJackScore()) + players.blackJackScores())

    val hasMorePlayablePlayer: Boolean
        get() = players.playable.isEmpty()

    val isDealerDrawMoreCard: Boolean
        get() = dealer.isDealerDrawMoreCard

    val names: List<Name>
        get() = buildList {
            add(dealer.name)
            addAll(players.names)
        }

    val isDealerBust: Boolean
        get() = dealer.participantInformation.isBust()

    fun ready() {
        dealer.ready(cardDeck)
        players.ready(cardDeck)
    }

    fun play(player: Player): BlackJackStatus {
        player.hit(cardDeck)
        return BlackJackStatus(player.name, player.cardsInHand.cards)
    }

    fun hitPlayers(hitAction: (Player) -> Unit) {
        players.hit(hitAction)
    }

    fun hitDealer() {
        dealer.hit(cardDeck)
    }

    fun winningResults(): BlackJackWinningResults {
        dealer.score(players.players)
        players.score(dealer)

        return winningResults
    }

    companion object {
        fun setup(players: Players): BlackJack {
            val cardDeck = setupCardDeck {
                spade()
                diamond()
                heart()
                club()
            }
            return BlackJack(Dealer.sit(), players, cardDeck)
        }
    }
}
