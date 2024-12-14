package blackjack.controller

import blackjack.domain.BlackJackResultManager
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackJackController {
    fun play() {
        val isDrawCard: (String) -> Boolean = { name ->
            val drawMoreCardYesOrNo = InputView.readIsDrawMore(name)
            drawMoreCardYesOrNo
        }
        val blackJackDealer = Dealer(cardDeck = CardDeck())

        val players =
            blackJackDealer.initPlayers(
                fetchPlayerNames = { InputView.readPlayerNames() },
                onPlayerInit = { names ->
                    ResultView.printPlayerInitMessage(names)
                    ResultView.printDealerWithCard(blackJackDealer.getCardForInitialDisplay())
                },
            )

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

        blackJackDealer.drawOneMoreCardIfNeeded(
            onDrawMoreCard = { ResultView.printDealerOneMoreCardDrawn() },
        )

        ResultView.printFinalScoresForDealer(blackJackDealer)
        players.onEach { player -> ResultView.printFinalScoresForPlayer(player) }

        val result = BlackJackResultManager(blackJackDealer, players).getResult()
        ResultView.printFinalWinLose(result)
    }
}
