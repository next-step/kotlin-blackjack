package blackjack

import blackjack.model.*
import blackjack.view.InputView
import blackjack.view.OutputView


class BlackJack {
    fun start() {
        val playerNames = InputView.getPlayerName()
        val cardDeck = CardDeck();
        val cardDealer = CardDealer(cardDeck);

        val players =
            Players(playerNames.map { Player(it, CardHand(cardDealer.getCards(CardDealer.FIRST_CARD_COUNT))) })

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
        while (getMoreCardOrNot(player.name) && player.cardHand.getTotalScore() < CardHand.BLACKJACK) {
            player.cardHand.addCard(cardDealer.getCard())
            OutputView.renderPlayer(player, ::println)
        }
    }

    private fun getMoreCardOrNot(name: String) = when (InputView.askGetCardMore(name)) {
        PlayAnswer.Y -> true
        PlayAnswer.N -> false
    }
}
