package blackjack.application

import blackjack.application.dto.BlackJackScore
import blackjack.application.dto.BlackJackWinningResult
import blackjack.application.dto.BlackJackWinningResults
import blackjack.application.dto.BlackjackScores
import blackjack.application.dto.BlackJackStatus
import blackjack.application.dto.BlackJackStatuses
import blackjack.domain.card.CardDeck
import blackjack.domain.card.setupCardDeck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players
import blackjack.domain.participant.vo.Name

private fun Dealer.status(): BlackJackStatus = BlackJackStatus(this.name, listOf(this.cardsInHand.cards.first()))

private fun Players.statuses(): List<BlackJackStatus> =
    players.map { BlackJackStatus(it.name, it.cardsInHand.cards) }

private fun Dealer.score(): BlackJackScore =
    BlackJackScore(this.name, this.cardsInHand.cards, this.cardsInHand.calculateScore())

private fun Players.scores(): List<BlackJackScore> {
    return players.map { BlackJackScore(it.name, it.cardsInHand.cards, it.cardsInHand.calculateScore()) }
}

private fun Dealer.winningResult(): BlackJackWinningResult =
    BlackJackWinningResult(this.name, this.winningScores)

private fun Players.winningResults(): List<BlackJackWinningResult> =
    players.map { BlackJackWinningResult(it.name, it.winningScores) }

class BlackJack private constructor(
    private val dealer: Dealer,
    private val players: Players,
    private val cardDeck: CardDeck
) {
    val statuses: BlackJackStatuses
        get() = BlackJackStatuses(
            buildList {
                add(dealer.status())
                addAll(players.statuses())
            }
        )

    private val winningResults: BlackJackWinningResults
        get() = BlackJackWinningResults(
            buildList {
                add(dealer.winningResult())
                addAll(players.winningResults())
            }
        )

    val scores: BlackjackScores
        get() = BlackjackScores(
            buildList {
                add(dealer.score())
                addAll(players.scores())
            }
        )

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
        if (isDealerBust) {
            return dealerBustWinningResults()
        }

        dealer.score(players.players)
        players.score(dealer)

        return winningResults
    }

    private fun dealerBustWinningResults(): BlackJackWinningResults {
        dealer.loseToBust(players.notBustCount)
        players.winToDealerBust()

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
