package blackjack.business

import blackjack.view.OutputHandler

object PlayActions {

    fun Players.dealInitialCards(cardDesk: CardDesk) {
        players.forEach { player ->
            repeat(2) { player.addCard(cardDesk.draw()) }
            OutputHandler.printPlayer(player)
        }
    }

    fun Players.processAdditionalCards(
        cardDesk: CardDesk,
        drawConditionStrategy: DrawConditionStrategy,
    ) {
        players.forEach { player ->
            while (player.canDrawCard() && drawConditionStrategy.shouldDraw(player.name)) {
                player.addCard(cardDesk.draw())
                OutputHandler.printPlayer(player)
            }
        }
    }

    fun Players.printResult() = players.forEach(OutputHandler::printResult)
}
