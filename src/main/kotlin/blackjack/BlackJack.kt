package blackjack

import blackjack.model.*
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJack {
    fun start() {
        val playerNames = InputView.getPlayerName()
        val cardDealer = CardDealer(CardDeck());
        val players = Players(playerNames.toPlayerList(cardDealer))
        val gameDealer = GameDealer(CardHand(cardDealer.getCards()))

        OutputView.renderInitMessage(playerNames)
        OutputView.renderPlayers(players.playerList + gameDealer)

        players.playerList.forEach {
            playGameOfEachPlayer(it, cardDealer)
        }

        playGameOfDealer(gameDealer, cardDealer)

        OutputView.renderResult(getGameResults(listOf(gameDealer) + players.playerList))
    }

    private fun getGameResults(cardHolders: List<CardHolder>) = cardHolders.map {
        GameResult(it, it.cardHand.totalScore)
    }

    private fun playGameOfEachPlayer(player: Player, cardDealer: CardDealer) {
        while (player.cardHand.isSameOrSmallerThanBlackJack && player.moreCardOrNot(InputView::askGetCardMore)) {
            player.cardHand.addCard(cardDealer.getCard())
            OutputView.renderPlayer(player, ::println)
        }
    }

    private fun playGameOfDealer(gameDealer: GameDealer, cardDealer: CardDealer) {
        while (gameDealer.isDealerShouldMoreCard) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            gameDealer.cardHand.addCard(cardDealer.getCard())
        }
    }

    private fun List<String>.toPlayerList(cardDealer: CardDealer) =
        this.map { Player(it, CardHand(cardDealer.getCards())) }
}
