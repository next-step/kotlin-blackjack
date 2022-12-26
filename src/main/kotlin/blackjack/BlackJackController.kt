package blackjack

import blackjack.domain.card.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView = InputView,
    private val gameDeck: Deck = Deck()
) {

    fun start() {
        // 선수 입장
        val players = inputView.enterPlayers()

        // 게임 시작
        players.startGame(gameDeck)

        // 플레이어들의 카드 공개
        OutputView.displayCards(players)

        // 블랙잭이 있는 경우 게임을 종료한다.
        players.checkBlackJack()

        // 플레이어들이 각자 턴을 플레이
        players.playEachTurn()

        // 게임 종료
        gameEnd(players)
    }

    private fun Players.playEachTurn() {
        // 게임이 끝나지 않은 유저가 있다면 게임을 지속한다.
        while (this.isAllFinished().not()) {
            val unfinishedPlayer = this.getUnfinishedPlayer()
            unfinishedPlayer.chooseDrawCard()
            OutputView.displayCards(unfinishedPlayer)
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
