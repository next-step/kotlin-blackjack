package blackjack.view

import blackjack.domain.BetAmount
import blackjack.domain.BlackjackGameTurn
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.domain.PlayerInfo
import blackjack.domain.PlayerName
import blackjack.view.input.InputView
import blackjack.view.input.UserInputRequest
import blackjack.view.input.converter.BetAmountConverter
import blackjack.view.input.converter.PlayerNamesConverter
import blackjack.view.input.converter.YesOrNoConverter
import blackjack.view.output.OutputView
import blackjack.view.output.converter.EndOfGameConverter
import blackjack.view.output.converter.PlayerConverter
import blackjack.view.output.converter.StartOfGameConverter
import blackjack.viewmodel.BlackjackViewModel

object BlackjackLayout {
    private const val GUIDANCE_MESSAGE_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val GUIDANCE_MESSAGE_BET_AMOUNT = "의 배팅 금액은?"
    private const val GUIDANCE_MESSAGE_HIT = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val GUIDANCE_MESSAGE_DEALER_HIT = "딜러는 16이하라 한 장의 카드를 더 받았습니다."
    private val DEALER_NAME = PlayerName("딜러")

    fun execute() {
        val playerInfos = getPlayerNames().map { playerName ->
            playerName.toPlayerInfo()
        }
        val viewModel = BlackjackViewModel.from(DEALER_NAME, playerInfos)
        OutputView.println(viewModel.participants, StartOfGameConverter)

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

    private fun PlayerName.toPlayerInfo(): PlayerInfo {
        return PlayerInfo(this, getBetAmount(this))
    }

    private fun getBetAmount(playerName: PlayerName): BetAmount {
        val userInputRequest = UserInputRequest(
            message = "${playerName.value}$GUIDANCE_MESSAGE_BET_AMOUNT",
            inputConverter = BetAmountConverter
        )

        return InputView.receiveUserInput(userInputRequest)
    }

    private fun takeTurn(turn: BlackjackGameTurn, viewModel: BlackjackViewModel) {
        while (!turn.isTurnEnd()) {
            stepOfTurn(turn, viewModel)
        }
    }

    private fun stepOfTurn(turn: BlackjackGameTurn, viewModel: BlackjackViewModel) {
        when (turn.participant) {
            is Player -> stepOfPlayerTurn(turn, viewModel)
            is Dealer -> stepOfDealerTurn(viewModel)
        }
    }

    private fun stepOfPlayerTurn(turn: BlackjackGameTurn, viewModel: BlackjackViewModel) {
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

    private fun stepOfDealerTurn(viewModel: BlackjackViewModel) {
        OutputView.printlnOnlyMessage(GUIDANCE_MESSAGE_DEALER_HIT)
        viewModel.hit()
    }
}
