package blackjack

import blackjack.ui.InputView
import global.strategy.ui.input.ConsoleInputStrategy
import global.strategy.ui.output.ConsoleOutputStrategy

class BlackJackApplication(private val inputView: InputView) {
    fun run() {
        val names = inputView.inputParticipantsInformation()

        /**
         * 사람 만들고 사람마다 y 동작
         * 사람이 가지고 있는 상태가 있으면 좋을 듯 -> Runninng, Finish
         * 상태패턴 추가
         */

        val y = inputView.inputWhetherAdditionalCardAcquisition()
    }
}

fun main() {
    val inputView = InputView(ConsoleInputStrategy, ConsoleOutputStrategy)
    BlackJackApplication(inputView).run()
}
