package blackjack

import blackjack.model.*
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJack {
    fun start() {
        /*
        * 초기화
        * */
        val playerNames = InputView.getPlayerName()
        val cardDealer = CardDealer(CardDeck());
        val gameDealer = GameDealer(IdGenerator.holderId, CardHand(cardDealer.getCards()))
        val players = Players(playerNames.toPlayerList(cardDealer))

        OutputView.renderInitMessage(playerNames)
        OutputView.renderPlayers(listOf(gameDealer) + players.playerList)


        /*
        * 게임 플레이
        * */
        players.playerList.forEach {
            playGameOfEachPlayer(it, cardDealer)
        }
        playGameOfDealer(gameDealer, cardDealer)


        /*
        * 결과 도출 및 출력
        * */
        OutputView.renderResult(listOf(gameDealer) + players.playerList)
        OutputView.renderResolved(
            GameResult(gameDealer, players).resolveGame()
        )
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
        this.map { Player(IdGenerator.holderId, it, CardHand(cardDealer.getCards())) }
}
