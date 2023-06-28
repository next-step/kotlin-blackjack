package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.InitCard
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.DealerCard
import blackjack.domain.gamer.PlayerCards
import blackjack.domain.gamer.PlayerNames
import blackjack.domain.gamer.Players
import blackjack.domain.shuffle.Shuffler

class BlackJackGame(
    shuffler: Shuffler<Card>,
    playerNames: PlayerNames,
) {

    private val cardDeck = CardDeck.create(shuffler)
    private val dealer = Dealer()
    private val players = Players.create(playerNames)

    fun currentTurn(): BlackJackGameTurn {
        return when {
            players.notHasCards() && dealer.notHasCards() -> {
                BlackJackGameTurn.CardDistribution
            }
            players.hasWaitPlayer() -> {
                BlackJackGameTurn.PlayerAnswer(players.requireWaitPlayer().name)
            }
            dealer.state.isInit() -> {
                BlackJackGameTurn.Dealer
            }
            else -> {
                BlackJackGameTurn.Finished
            }
        }
    }

    fun distributeCardsToPlayers(): CardDistributionResult {
        requireTurn<BlackJackGameTurn.CardDistribution>()

        dealer.init(InitCard.create(cardDeck.pick(CARD_DISTRIBUTION_SIZE)))
        players.init { InitCard.create(cardDeck.pick(CARD_DISTRIBUTION_SIZE)) }

        return CardDistributionResult(
            dealerCards = listOf(DealerCard.Open(dealer.state.cards.first()), DealerCard.Hide),
            playerCards = players.captureAllPlayerCards(),
        )
    }

    fun hitFocusedPlayer(): PlayerCards {
        requireTurn<BlackJackGameTurn.PlayerAnswer>()

        val player = players.requireWaitPlayer()
        player.hit(cardDeck.pick())
        return player.captureCards()
    }

    fun stayFocusedPlayer() {
        requireTurn<BlackJackGameTurn.PlayerAnswer>()

        players.requireWaitPlayer().stay()
    }

    fun executeDealerTurn(): DealerTurnExecuteResult {
        requireTurn<BlackJackGameTurn.Dealer>()

        val isDistributedOneMoreCard = if (dealer.canHit()) {
            dealer.hit(cardDeck.pick())
            true
        } else {
            dealer.stay()
            false
        }

        return DealerTurnExecuteResult(
            isDistributedOneMoreCard = isDistributedOneMoreCard,
        )
    }

    fun makeGameResult(): MatchResult {
        requireTurn<BlackJackGameTurn.Finished>()

        return players.match(dealer)
    }

    private inline fun <reified T> requireTurn() {
        val turn = currentTurn()
        require(turn is T) {
            "you want turn is '${turn::class.java.simpleName}'. but current turn is '${T::class.java.simpleName}'"
        }
    }

    companion object {

        private const val CARD_DISTRIBUTION_SIZE = 2
    }
}
