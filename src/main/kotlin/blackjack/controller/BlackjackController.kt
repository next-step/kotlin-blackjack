package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.user.Player
import blackjack.dto.PlayerDto
import blackjack.dto.UserDto
import blackjack.dto.toDto
import blackjack.service.InputParser
import blackjack.view.BlackjackInputView
import blackjack.view.BlackjackResultView
import blackjack.view.BlackjackView
import blackjack.view.DealerView
import blackjack.view.PlayerView
import blackjack.view.UserView

class BlackjackController {

    fun start() {
        BlackjackView.printPlayerInput()
        val players = getPlayers()

        val blackjack = Blackjack(players)
        blackjack.drawFirstCards()
        BlackjackView.printInitialize(blackjack.players.map { PlayerDto.of(it, blackjack.dealer.versus(it)) })
        DealerView.printCard(UserDto.of(blackjack.dealer), blackjack.dealer.getFirstCard())
        BlackjackView.printCards(blackjack.toDto())
        startGame(blackjack)
        BlackjackView.printResult(blackjack.toDto())
        BlackjackResultView.printResult(blackjack.toDto())
    }

    private fun getPlayers(): List<Player> {
        val names = BlackjackInputView.readPlayerNames()

        return InputParser.parsePlayerName(names).map(::Player)
    }

    private fun startGame(blackjack: Blackjack) {
        blackjack.players.forEach {
            drawUntilFinish(blackjack, it)
        }

        val dealer = blackjack.dealer
        while (dealer.drawable()) {
            blackjack.drawCard(dealer)
            DealerView.printMoreCard(UserDto.of(dealer))
            UserView.printCards(UserDto.of(dealer))
        }
    }

    private fun drawUntilFinish(blackjack: Blackjack, player: Player) {
        while (true) {
            if (!player.drawable()) return PlayerView.printCanNotDrawCard(UserDto.of(player))

            PlayerView.printMoreCard(UserDto.of(player))

            if (askMoreCard()) blackjack.drawCard(player)
            else return UserView.printCards(UserDto.of(player))

            UserView.printCards(UserDto.of(player))
        }
    }

    private fun askMoreCard(): Boolean {
        return InputParser.parseMoreCard(BlackjackInputView.readMoreCard())
    }
}
