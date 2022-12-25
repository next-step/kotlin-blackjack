package blackjack

import blackjack.domain.card.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.system.System
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView = InputView,
    private val gameSystem: System = System(),
    private val gameDeck: Deck = Deck
) {

    fun start() {
        // 선수 입장
        val players = inputView.enterPlayers()

        // 게임 시작
        players.startGame(gameDeck)

        // 플레이어들의 카드 공개
        OutputView.displayCards(players)

        // 블랙잭이 있는 경우 게임을 종료한다.
        if (gameSystem.checkBlackJack(players)) {
            gameEnd(players)
        }

        // 플레이어들이 각자 턴을 플레이
        players.playEachTurn()

        // 게임 종료
        gameEnd(players)
    }

    private fun Players.playEachTurn() {
        this.players.forEach { player ->
            // 플레이어들이 각자 카드를 더 받을지 턴을 마칠지 결정
            while (player.isFinished().not()) {
                player.chooseDrawCard()
                OutputView.displayCards(player)
            }
        }
    }

    private fun Player.chooseDrawCard() {
        when (inputView.chooseDrawCard(this)) {
            true -> this.hit(gameDeck.draw())
            false -> this.stay()
        }
    }

    private fun gameEnd(players: Players) {
        OutputView.displayGameResult(players)
    }
}
