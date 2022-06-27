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

        Blackjack(players).let {
            it.drawFirstCards()
            BlackjackView.printInitialize(it.players.map { player -> PlayerDto.of(player, it.dealer.versus(player)) })
            BlackjackView.printCards(it.toDto())
            startGame(it)
            BlackjackView.printResult(it.toDto())
            BlackjackResultView.printResult(it.toDto())
        }
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
