package blackjack.domain.gamer

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.game.CardDistributionResult
import blackjack.domain.game.DealerTurnExecuteResult
import blackjack.domain.game.MatchResult

class Gamers(
    private val dealer: Dealer,
    private val players: Players,
) {

    fun init(pickCards: () -> Cards): CardDistributionResult {
        dealer.init(pickCards())
        players.init { pickCards() }

        return CardDistributionResult(
            dealerCards = listOf(DealerCard.Open(dealer.getFirstCard()), DealerCard.Hide),
            playerCards = players.captureAllPlayerCards(),
        )
    }

    fun hitToFocusedPlayer(card: Card): PlayerCards {
        val player = players.requireWaitPlayer()
        player.hit(card)
        return player.captureCards()
    }

    fun stayToFocusedPlayer() {
        players.requireWaitPlayer().stay()
    }

    fun tryHitToDealer(pickCard: () -> Card): DealerTurnExecuteResult {
        val isDistributedOneMoreCard = if (dealer.canHit()) {
            dealer.hit(pickCard())
            true
        } else {
            dealer.stay()
            false
        }
        return DealerTurnExecuteResult(
            isDistributedOneMoreCard = isDistributedOneMoreCard,
        )
    }

    fun hasNotCards(): Boolean {
        return players.notHasCards() && dealer.notHasCards()
    }

    fun hasWaitPlayer(): Boolean {
        return players.hasWaitPlayer()
    }

    fun requireWaitPlayer(): Player {
        return players.requireWaitPlayer()
    }

    fun isDealerWait(): Boolean {
        return dealer.state.isInit()
    }

    fun match(): MatchResult {
        return MatchResult.create(players, dealer)
    }

    companion object {

        fun create(playerInitProperties: List<PlayerInitProperty>): Gamers {
            return Gamers(
                dealer = Dealer(),
                players = Players.create(playerInitProperties)
            )
        }
    }
}
