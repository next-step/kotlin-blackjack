package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.InitCard
import blackjack.domain.gamer.Gamers
import blackjack.domain.gamer.PlayerCards
import blackjack.domain.gamer.PlayerNames
import blackjack.domain.shuffle.Shuffler

class BlackJackGame(
    shuffler: Shuffler<Card>,
    playerNames: PlayerNames,
) {

    private val cardDeck = CardDeck.create(shuffler)
    private val gamers = Gamers.create(playerNames)

    fun currentTurn(): BlackJackGameTurn {
        return when {
            gamers.hasNotCards() -> {
                BlackJackGameTurn.CardDistribution
            }
            gamers.hasWaitPlayer() -> {
                BlackJackGameTurn.PlayerAnswer(gamers.requireWaitPlayer().name)
            }
            gamers.isDealerWait() -> {
                BlackJackGameTurn.Dealer
            }
            else -> {
                BlackJackGameTurn.Finished
            }
        }
    }

    fun distributeCardsToPlayers(): CardDistributionResult {
        requireTurn<BlackJackGameTurn.CardDistribution>()
        return gamers.init { InitCard.create(listOf(cardDeck.pick(), cardDeck.pick())) }
    }

    fun hitFocusedPlayer(): PlayerCards {
        requireTurn<BlackJackGameTurn.PlayerAnswer>()
        return gamers.hitToFocusedPlayer(cardDeck.pick())
    }

    fun stayFocusedPlayer() {
        requireTurn<BlackJackGameTurn.PlayerAnswer>()
        gamers.stayToFocusedPlayer()
    }

    fun executeDealerTurn(): DealerTurnExecuteResult {
        requireTurn<BlackJackGameTurn.Dealer>()
        return gamers.tryHitToDealer { cardDeck.pick() }
    }

    fun makeGameResult(): MatchResult {
        requireTurn<BlackJackGameTurn.Finished>()
        return gamers.match()
    }

    private inline fun <reified T> requireTurn() {
        val turn = currentTurn()
        require(turn is T) {
            "you want turn is '${turn::class.java.simpleName}'. but current turn is '${T::class.java.simpleName}'"
        }
    }
}
