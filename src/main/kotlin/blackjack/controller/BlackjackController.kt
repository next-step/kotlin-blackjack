package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.player.GamePlayers
import blackjack.domain.player.Player
import blackjack.view.input.GamePlayerNameInputView
import blackjack.view.input.GamePlayerReceiveInputView
import blackjack.view.output.GameDealerAddCardOutputView
import blackjack.view.output.GamePlayersOutputView
import blackjack.view.output.GamePlayersResultOutputView
import blackjack.view.output.GameSharedCardOutputView
import blackjack.view.output.NewLineOutputView

class BlackjackController {
    fun start() {
        // 게임 플레이어 이름 입력
        val playerNameList = GamePlayerNameInputView().value
        NewLineOutputView()

        val gamePlayers = GamePlayers(playerNameList.map { Player(it) })
        val blackjackGame = BlackjackGame(gamePlayers)
        blackjackGame.initPlayers()
        // 딜러, 플레이어가 보유한 카드 현황 출력
        GameSharedCardOutputView(gamePlayers)
        GamePlayersOutputView(gamePlayers)
        NewLineOutputView()

        // 각 플레이어마다 카드 분배 로직 수행
        gamePlayers.value.forEach { player -> dealCards(player, blackjackGame) }
        NewLineOutputView()

        // 딜러의 score 여부에 따라 추가 카드 분배 현황 출력
        GameDealerAddCardOutputView(gamePlayers.dealer)
        if (gamePlayers.dealer.isReceivable()) blackjackGame.dealCard(gamePlayers.dealer)

        // 딜러, 플레이어가 보유한 카드현황 + 최종 score 출력
        GamePlayersOutputView(gamePlayers, true)

        // 최종 승패 출력
        GamePlayersResultOutputView(gamePlayers)
    }

    private fun dealCards(player: Player, blackjackGame: BlackjackGame) {
        while (player.isReceivable()) {
            val response = GamePlayerReceiveInputView(player.name).value
            if (!response.value) {
                player.getStayStatus()
                return
            }
            blackjackGame.dealCard(player)
            GamePlayersOutputView(GamePlayers.from(player))
        }
    }
}
