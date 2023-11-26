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
        val cardDispenser = CardDispenser(CardDeck())
        val gameDealer =
            CardHolder.GameDealer(IdGenerator.holderId, CardHand(cardDispenser.getCards(CardHand.FIRST_COUNT)))
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
        players.playerList.forEach {
            Judge.resolve(gameDealer, it)
        }

        OutputView.renderResult(listOf(gameDealer) + players.playerList)
    }

    private fun playGameOfEachPlayer(player: CardHolder.Player, cardDispenser: CardDispenser) {
        while (!player.isBust && player.hitOrNot(InputView::askGetCardMore)) {
            player.cardHand.addCard(cardDispenser.getCard())
            OutputView.renderPlayer(player, ::println)
            if (player.isBust) {
                println("${player.name}는 버스트하여 더이상 게임에 참여할 수 없습니다")
            }
        }
    }

    private fun playGameOfDealer(gameDealer: CardHolder.GameDealer, cardDispenser: CardDispenser) {
        if (gameDealer.isDealerShouldMoreCard) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            gameDealer.cardHand.addCard(cardDispenser.getCard())
        }
    }

    private fun List<String>.toPlayerList(cardDispenser: CardDispenser) =
        this.map { CardHolder.Player(IdGenerator.holderId, CardHand(cardDispenser.getCards(CardHand.FIRST_COUNT)), it) }
}
