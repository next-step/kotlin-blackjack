package blackjack.view

import blackjack.domain.BlackjackGameTurn
import blackjack.domain.Participant
import blackjack.domain.PlayerName
import blackjack.view.input.InputView
import blackjack.view.input.UserInputRequest
import blackjack.view.input.converter.PlayerNamesConverter
import blackjack.view.input.converter.YesOrNoConverter
import blackjack.view.output.OutputView
import blackjack.view.output.converter.EndOfGameConverter
import blackjack.view.output.converter.PlayerConverter
import blackjack.view.output.converter.StartOfGameConverter
import blackjack.viewmodel.BlackjackViewModel

object BlackjackLayout {
    private const val GUIDANCE_MESSAGE_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val GUIDANCE_MESSAGE_HIT = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private val DEALER_NAME = PlayerName("딜러")

    fun execute() {
        val viewModel = BlackjackViewModel.from(DEALER_NAME, getPlayerNames())
        OutputView.println(viewModel.participants.all, StartOfGameConverter)

        viewModel.currentTurn.observe { turn ->
            if (turn.isTurnEnd()) return@observe

            takeTurn(turn, viewModel)

            viewModel.nextTurn()
        }

        println()
        OutputView.print(viewModel.getBlackjackGameResult(), EndOfGameConverter)
    }

    private fun getPlayerNames(): List<PlayerName> {
        val userInputRequest = UserInputRequest(
            message = GUIDANCE_MESSAGE_PLAYERS_NAME,
            inputConverter = PlayerNamesConverter
        )

        return InputView.receiveUserInput(userInputRequest)
    }

    private fun takeTurn(turn: BlackjackGameTurn, viewModel: BlackjackViewModel) {
        while (turn.isTurnEnd()) {
            stepOfTurn(turn, viewModel)
        }
    }

    private fun stepOfTurn(turn: BlackjackGameTurn, viewModel: BlackjackViewModel) {
        if (isPlayerWannaHit(turn.participant)) {
            viewModel.hit()
            OutputView.print(turn.participant, PlayerConverter)
        } else {
            viewModel.stay()
        }
    }

    private fun isPlayerWannaHit(participant: Participant): Boolean {
        val userInputRequest = UserInputRequest(
            message = "${participant.name.value}$GUIDANCE_MESSAGE_HIT",
            inputConverter = YesOrNoConverter
        )

        return InputView.receiveUserInput(userInputRequest)
    }
}
