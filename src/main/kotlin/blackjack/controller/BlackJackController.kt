package blackjack.controller

import blackjack.domain.Dealer
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackJackController {
    fun play() {
        val isDrawCard: (String) -> Boolean = { name ->
            val drawMoreCardYesOrNo = InputView.readIsDrawMore(name)
            drawMoreCardYesOrNo
        }
        val blackJackDealer = Dealer()

        blackJackDealer.initParticipants(
            fetchPlayerNames = { InputView.readPlayerNames() },
            onPlayerInit = { names -> ResultView.printPlayerInitMessage(names) },
        ).onPreparePlay { dealer, player ->
            ResultView.printDealerWithCard(dealer.dealerCards.value[0])
            ResultView.printPlayerNameWithCards(player)
        }.onStartPlay { dealer, player ->
            player.play(
                isDrawCard = isDrawCard,
                onDrawCard = { ResultView.printPlayerNameWithCards(player) },
                onExitPlay = { ResultView.printPlayerNameWithCards(player) },
            )
            dealer.play(
                onDrawMoreCard = {
                    ResultView.printDealerOneMoreCardDrawn()
                }
            )
        }.onEndPlay { dealer, player ->
            ResultView.printFinalScores(player)
        }
    }
}
