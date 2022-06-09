package blackjack.application

import blackjack.application.dto.BlackJackResult
import blackjack.application.dto.BlackJackRoundResult
import blackjack.application.dto.BlackJackRoundResults
import blackjack.application.dto.BlackjackResults
import blackjack.application.dto.BlackJackStatus
import blackjack.application.dto.BlackJackStatuses
import blackjack.domain.card.CardDeck
import blackjack.domain.card.setupCardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.vo.Name

private fun Dealer.getPlayerStatus(): BlackJackStatus = BlackJackStatus(this.name, listOf(this.cardsInHand.cards.first()))

private fun Players.getPlayerStatuses(): List<BlackJackStatus> =
    players.map { BlackJackStatus(it.name, it.cardsInHand.cards) }

private fun Dealer.getPlayerResult(): BlackJackResult =
    BlackJackResult(this.name, this.cardsInHand.cards, this.cardsInHand.calculateScore())

private fun Players.getPlayerResults(): List<BlackJackResult> {
    return players.map { BlackJackResult(it.name, it.cardsInHand.cards, it.cardsInHand.calculateScore()) }
}

private fun Dealer.getRoundResult(players: List<Player>): BlackJackRoundResult =
    BlackJackRoundResult(this.name, this.score(players))

private fun Players.getRoundResults(dealer: Dealer): List<BlackJackRoundResult> {
    val gamers = buildList {
        addAll(players)
        add(dealer)
    }
    return players.map { BlackJackRoundResult(it.name, it.score(gamers)) }
}

class BlackJack private constructor(
    private val dealer: Dealer,
    private val players: Players,
    private val cardDeck: CardDeck
) {
    val statuses: BlackJackStatuses
        get() = BlackJackStatuses(
            buildList {
                add(dealer.getPlayerStatus())
                addAll(players.getPlayerStatuses())
            }
        )

    val results: BlackjackResults
        get() = BlackjackResults(
            buildList {
                add(dealer.getPlayerResult())
                addAll(players.getPlayerResults())
            }
        )
    val rounds: BlackJackRoundResults
        get() = BlackJackRoundResults(
            buildList {
                add(dealer.getRoundResult(players.players))
                addAll(players.getRoundResults(dealer))
            }
        )

    val isPlayerAllStay: Boolean
        get() = players.allStay

    val isDealerStay: Boolean
        get() = dealer.isStay()

    val names: List<Name>
        get() = buildList {
            add(dealer.name)
            addAll(players.names)
        }

    val dealerBust: Boolean
        get() = dealer.isBust

    fun ready() {
        dealer.ready(cardDeck)
        players.ready(cardDeck)
        if (dealer.isBust) {
            players.winToDealerBust()
            return
        }
    }

    fun play(player: Player): BlackJackStatus {
        player.hit(cardDeck)
        return BlackJackStatus(player.name, player.cardsInHand.cards)
    }

    fun playerHit(hit: (Player) -> Unit) {
        players.hittable.forEach { hit(it) }
    }

    fun hitDealer(hitAfterAction: () -> Unit) {
        dealer.hit(cardDeck)
        hitAfterAction()
        if (dealer.isBust) {
            dealer.loseToBust()
            players.winToDealerBust()
        }
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
