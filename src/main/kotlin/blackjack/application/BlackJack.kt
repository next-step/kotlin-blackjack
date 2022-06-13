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
import blackjack.domain.participant.vo.Name

class BlackJack private constructor(
    private val participants: BlackJackParticipants,
    private val cardDeck: CardDeck
) {
    val statuses: BlackJackStatuses
        get() = participants.statuses

    val scores: BlackjackScores
        get() = participants.scores

    val hasMorePlayablePlayer: Boolean
        get() = participants.hasMorePlayablePlayer

    val isDealerDrawMoreCard: Boolean
        get() = participants.isDealerDrawMoreCard

    val participantsNames: List<Name>
        get() = participants.names

    val isDealerBust: Boolean
        get() = participants.isDealerBust

    fun ready() {
        participants.ready(cardDeck)
    }

    fun play(player: Player): BlackJackStatus {
        player.hit(cardDeck)
        return BlackJackStatus(player.name, player.cardsInHand.cards)
    }

    fun hitPlayers(hitAction: (Player) -> Unit) {
        participants.hitPlayers(hitAction)
    }

    fun hitDealer() {
        participants.hitDealer(cardDeck)
    }

    fun winningResults(): BlackJackWinningResults {
        return participants.winningResults()
    }

    companion object {
        fun setup(players: Players): BlackJack {
            val cardDeck = setupCardDeck {
                spade()
                diamond()
                heart()
                club()
            }
            return BlackJack(BlackJackParticipants(Dealer.sit(), players), cardDeck)
        }
    }
}
