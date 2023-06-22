package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.CardDeck
import blackjack.domain.Player

class BlackjackController {
    fun start() {
        val deck = CardDeck()
        val player1 = Player()
        val player2 = Player()
        val blackjackGame = BlackjackGame(listOf(player1, player2), deck)

        // TODO: (Output) pobi, jason에게 2장의 나누었습니다.
        blackjackGame.initPlayers()
        // TODO: (Output) pobi카드: 2하트, 8스페이드
    }

    fun shareCard(player: Player, blackjackGame: BlackjackGame) {
        while (player.isReceivable())
            // TODO: (Output) pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
            // TODO: (Input) y
            ;
        val isReceiveCard = true
        if (!isReceiveCard) return
        blackjackGame.shareCard(player)
        // TODO: (Output) pobi카드: 2하트, 8스페이드, A클로버
    }
}
