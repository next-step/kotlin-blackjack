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
        val cardDispenser = CardDispenser(CardDeck());
        val gameDealer = GameDealer(IdGenerator.holderId, CardHand(cardDispenser))
        val players = Players(playerNames.toPlayerList(cardDispenser))

        OutputView.renderInitMessage(playerNames)
        OutputView.renderFirstDealerHand(gameDealer, ::println)
        OutputView.renderPlayers(players.playerList)

        /*
        * 게임 플레이
        * */
        players.playerList.forEach {
            playGameOfEachPlayer(it, cardDispenser)
        }
        playGameOfDealer(gameDealer, cardDispenser)


        /*
        * 결과 도출 및 출력
        * */
        OutputView.renderResult(listOf(gameDealer) + players.playerList)
        OutputView.renderResolved(
            GameResult(gameDealer, players).resolveGame()
        )
    }

    private fun playGameOfEachPlayer(player: Player, cardDispenser: CardDispenser) {
        while (!player.cardHand.bust && player.moreCardOrNot(InputView::askGetCardMore)) {
            player.cardHand.addCard(cardDispenser.getCard())
            OutputView.renderPlayer(player, ::println)
        }
    }

    private fun playGameOfDealer(gameDealer: GameDealer, cardDispenser: CardDispenser) {
        if (gameDealer.isDealerShouldMoreCard) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            gameDealer.cardHand.addCard(cardDispenser.getCard())
        }
    }

    private fun List<String>.toPlayerList(cardDispenser: CardDispenser) =
        this.map { Player(IdGenerator.holderId, it, CardHand(cardDispenser)) }
}
