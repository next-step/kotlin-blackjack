package blackjack.controller

import blackjack.domain.card.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.strategy.draw.DrawStrategy
import blackjack.domain.strategy.draw.HitDrawStrategy
import blackjack.domain.strategy.draw.InitialDrawStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

object GameController {

    private val cardDeck = CardDeck()

    fun start() {
        var dealer = Dealer()
        val playerNames = InputView.inputPlayerNames()
        var players = Players.from(Players.getPlayerListByNames(playerNames))
        dealer = dealer.draw(cardDeck, InitialDrawStrategy)
        players = players.drawCardEachPlayer(cardDeck, InitialDrawStrategy)

        OutputView.printHowManyCardsPlayerDrawn(dealer, players)
        OutputView.printPlayersDrawnCards(dealer, players)

        players = players.drawIfAskPlayerWantsToDraw(cardDeck, HitDrawStrategy)

        dealer = drawDealer(dealer, HitDrawStrategy)

        OutputView.printScoreResult(dealer, players)
        printDealerResults(dealer, players)
        printPlayersResults(dealer, players)
    }

    private fun Players.drawIfAskPlayerWantsToDraw(
        cardDeck: CardDeck,
        drawStrategy: DrawStrategy
    ): Players {
        return players
            .map {
                drawOrStay(it, cardDeck, drawStrategy)
            }
            .let { Players.from(it) }
    }

    private fun drawOrStay(
        player: Player,
        cardDeck: CardDeck,
        drawStrategy: DrawStrategy
    ): Player {
        var nowPlayer = player
        while (!nowPlayer.isFinished) {
            nowPlayer = if (InputView.askPlayerWantsToDraw(player)) {
                nowPlayer.draw(cardDeck, drawStrategy).also {
                    OutputView.printPlayerDrawnCard(it)
                }
            } else {
                nowPlayer.stay()
            }
        }
        return nowPlayer
    }

    private fun drawDealer(dealer: Dealer, drawStrategy: DrawStrategy): Dealer {
        return if (dealer.canHit()) {
            OutputView.printDealerDraw()
            val state = dealer.draw(cardDeck, drawStrategy)
            if (!state.isBust) {
                state.stay()
            } else {
                state
            }
        } else {
            dealer.stay()
        }
    }

    private fun printDealerResults(dealer: Dealer, players: Players) {
        GameResult
            .makeGameResult(dealer, players)
            .also {
                OutputView.printDealerResult(it)
            }
    }

    private fun printPlayersResults(dealer: Dealer, players: Players) {
        players.players.forEach {
            OutputView.printPlayerResult(it, it.match(dealer))
        }
    }
}
