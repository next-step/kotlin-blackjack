package blackjack.controller

import blackjack.domain.GamePlayUsers
import blackjack.domain.deck.CardDeck
import blackjack.domain.entity.Player
import blackjack.domain.setup.GameStartSetting
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller(
    private val cardDeck: CardDeck = CardDeck()
) {

    fun gameStart(): GamePlayUsers {

        val players = InputView.setPlayer()

        val gamePlayUsers = GamePlayUsers(GameStartSetting.setGame(players, cardDeck))

        OutputView.printSettingPlayer(gamePlayUsers.playUsers)

        return gamePlayUsers
    }

    fun playing(player: Player) {
        while (InputView.hitsAndStay(player.name) && player.scoreCalculation() < BLACK_JACK_SCORE) {
            player.hits(cardDeck.draw())
            OutputView.printPlayCard(player)
        }
    }

    fun gameEnd(gamePlayUsers: GamePlayUsers) {
        OutputView.gameEnd(gamePlayUsers.playUsers)
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
