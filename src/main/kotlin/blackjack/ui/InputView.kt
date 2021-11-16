package blackjack.ui

import blackjack.strategy.ui.input.InputStrategy
import blackjack.strategy.ui.output.OutputStrategy

class InputView(private val inputStrategy: InputStrategy, private val outputStrategy: OutputStrategy) {

    fun inputParticipantsInformation(): String {
        outputStrategy.execute(PARTICIPANTS_INFORMATION_INPUT_MESSAGE)
        return inputStrategy.execute()
    }

    fun inputWhetherAdditionalCardAcquisition(name: String): String {
        outputStrategy.execute(ADDITIONAL_CARD_ACQUISITION_INPUT_MESSAGE.format(name))
        return inputStrategy.execute()
    }

    companion object {
        private const val PARTICIPANTS_INFORMATION_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val ADDITIONAL_CARD_ACQUISITION_INPUT_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    }
}
