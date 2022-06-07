package blackjack.view

import blackjack.domain.Player
import blackjack.view.input.InputView
import blackjack.view.input.UserInputRequest
import blackjack.view.input.converter.PlayersConverter
import blackjack.view.input.converter.YesOrNoConverter
import blackjack.view.output.OutputView
import blackjack.view.output.converter.EndOfGameConverter
import blackjack.view.output.converter.PlayerConverter
import blackjack.view.output.converter.StartOfGameConverter
import blackjack.viewmodel.BlackjackViewModel

object BlackjackLayout {
    private const val GUIDANCE_MESSAGE_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val GUIDANCE_MESSAGE_HIT = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun execute() {
        val viewModel = BlackjackViewModel.from(getPlayers())
        OutputView.println(viewModel.players, StartOfGameConverter)

        viewModel.currentTurn.observe { player ->
            player ?: return@observe

            takeTurn(player, viewModel)

            viewModel.nextTurn()
        }

        println()
        OutputView.print(viewModel.players, EndOfGameConverter)
    }

    private fun getPlayers(): List<Player> {
        val userInputRequest = UserInputRequest(
            message = GUIDANCE_MESSAGE_PLAYERS_NAME,
            inputConverter = PlayersConverter
        )

        return InputView.receiveUserInput(userInputRequest)
    }

    private fun takeTurn(player: Player, viewModel: BlackjackViewModel) {
        while (player.isReceivable()) {
            stepOfTurn(player, viewModel)
        }
    }

    private fun stepOfTurn(player: Player, viewModel: BlackjackViewModel) {
        if (isPlayerWannaHit(player)) {
            viewModel.hit()
            OutputView.print(player, PlayerConverter)
        } else {
            viewModel.stay()
        }
    }

    private fun isPlayerWannaHit(player: Player): Boolean {
        val userInputRequest = UserInputRequest(
            message = "${player.name}$GUIDANCE_MESSAGE_HIT",
            inputConverter = YesOrNoConverter
        )

        return InputView.receiveUserInput(userInputRequest)
    }
}
