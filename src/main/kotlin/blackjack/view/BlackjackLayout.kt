package blackjack.view

import blackjack.Contract
import blackjack.domain.BetAmount
import blackjack.domain.BlackjackGameResult
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.presenter.BlackjackPresenter
import blackjack.view.input.InputView
import blackjack.view.input.UserInputRequest
import blackjack.view.input.converter.BetAmountConverter
import blackjack.view.input.converter.PlayerNamesConverter
import blackjack.view.input.converter.YesOrNoConverter
import blackjack.view.output.OutputView
import blackjack.view.output.converter.EndOfGameConverter
import blackjack.view.output.converter.PlayerConverter
import blackjack.view.output.converter.StartOfGameConverter

object BlackjackLayout : Contract.View {
    private const val GUIDANCE_MESSAGE_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val GUIDANCE_MESSAGE_BET_AMOUNT = "의 배팅 금액은?"
    private const val GUIDANCE_MESSAGE_HIT = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val GUIDANCE_MESSAGE_DEALER_HIT = "딜러는 16이하라 한 장의 카드를 더 받았습니다."

    fun execute() {
        val presenter: Contract.Presenter = BlackjackPresenter.from(this)
        presenter.startGame()
    }

    override fun getPlayerNames(): List<PlayerName> {
        val userInputRequest = UserInputRequest(
            message = GUIDANCE_MESSAGE_PLAYERS_NAME,
            inputConverter = PlayerNamesConverter
        )

        return InputView.receiveUserInput(userInputRequest)
    }

    override fun getBetAmount(playerName: PlayerName): BetAmount {
        val userInputRequest = UserInputRequest(
            message = "${playerName.value}$GUIDANCE_MESSAGE_BET_AMOUNT",
            inputConverter = BetAmountConverter
        )

        return InputView.receiveUserInput(userInputRequest)
    }

    override fun isPlayerWannaHit(playerName: PlayerName): Boolean {
        val userInputRequest = UserInputRequest(
            message = "${playerName.value}$GUIDANCE_MESSAGE_HIT",
            inputConverter = YesOrNoConverter
        )

        return InputView.receiveUserInput(userInputRequest)
    }

    override fun showStartOfGameInfo(participants: Participants) {
        OutputView.println(participants, StartOfGameConverter)
    }

    override fun showPlayersHitEvent(player: Player) {
        OutputView.println(player, PlayerConverter)
    }

    override fun showDealerHitEvent() {
        OutputView.printlnOnlyMessage(GUIDANCE_MESSAGE_DEALER_HIT)
    }

    override fun showEndOfGameInfo(blackjackGameResult: BlackjackGameResult) {
        OutputView.print(blackjackGameResult, EndOfGameConverter)
    }
}
