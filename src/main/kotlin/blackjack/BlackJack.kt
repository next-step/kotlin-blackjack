package blackjack

import blackjack.model.*
import blackjack.view.InputView
import blackjack.view.OutputView


class BlackJack {
    fun start() {
        val playerNames = InputView.getPlayerName()
        val cardDealer = CardDealer(CardDeck());
        val players = Players(playerNames.toPlayerList(cardDealer))

        OutputView.renderPlayers(players.playerList)

        players.playerList.forEach {
            playGameWithEachPlayer(it, cardDealer)
        }

        OutputView.renderResult(getGameResults(players.playerList))
    }

    private fun getGameResults(playerList: List<Player>) = playerList.map {
        GameResult(it, it.cardHand.getTotalScore())
    }

    private fun playGameWithEachPlayer(player: Player, cardDealer: CardDealer) {
        while (player.cardHand.isSameOrSmallerThanBlackJack() && player.moreCardOrNot(InputView::askGetCardMore)) {
            player.cardHand.addCard(cardDealer.getCard())
            OutputView.renderPlayer(player, ::println)
        }
    }

    private fun List<String>.toPlayerList(cardDealer: CardDealer) =
        this.map { Player(it, CardHand(cardDealer.getCards())) }
}
