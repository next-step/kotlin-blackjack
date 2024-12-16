package blackjack.controller

import blackjack.domain.BlackJackResultManager
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackJackController {
    fun play() {
        val cardDeck = CardDeck()
        val blackJackDealer = Dealer(drawCard = { cardDeck.draw() })
        val players = initializeGame(blackJackDealer)

        playTurns(players)
        finalizeDealer(blackJackDealer)
        finalizeGame(blackJackDealer, players)
    }

    private fun initializeGame(dealer: Dealer): Players {
        val players =
            dealer.initPlayers(
                fetchPlayerNames = { InputView.readPlayerNames() },
                getBettingAmount = { name -> InputView.readBettingAmount(name) },
                onPlayerInit = { names ->
                    ResultView.printPlayerInitMessage(names)
                    ResultView.printDealerWithCard(dealer.getCardForInitialDisplay())
                },
            )
        return players
    }

    private fun playTurns(players: Players) {
        val isDrawCard: (String) -> Boolean = { name ->
            InputView.readIsDrawMore(name)
        }

        players
            .onEachPreparePlay { player ->
                ResultView.printPlayerNameWithCards(player)
            }.onEachStartPlay { player ->
                player.play(
                    isDrawCard = isDrawCard,
                    onDrawCard = { ResultView.printPlayerNameWithCards(player) },
                    onExitPlay = { ResultView.printPlayerNameWithCards(player) },
                )
            }
    }

    private fun finalizeDealer(dealer: Dealer) {
        dealer.drawOneMoreCardIfNeeded(onDrawCard = { ResultView.printDealerOneMoreCardDrawn() })
        ResultView.printFinalScoresForDealer(dealer)
    }

    private fun finalizeGame(
        dealer: Dealer,
        players: Players,
    ) {
        players.onEach { player ->
            ResultView.printFinalScoresForPlayer(player)
        }

        val result = BlackJackResultManager(dealer, players).getResult()
        ResultView.printFinalWinLose(result)
    }
}
