package blackjack.ui

import global.strategy.ui.output.OutputStrategy

class OutPutView(private val outputStrategy: OutputStrategy) {

    companion object {
        private const val THE_RESULT_MESSAGE_OF_HANDING_OUT_CARDS = "%S, %s에게 2장의 나누었습니다."
        private const val CARDS_HELD_BY_THE_PARTICIPANT_INFORMATIONAL_MESSAGE = "%s카드: %s"
        private const val RESULT_MESSAGE = "%s카드: %s - 결과: %s"
    }
}
