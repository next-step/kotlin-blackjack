package blackjack

import blackjack.domain.Command
import blackjack.ui.InputView
import global.strategy.split.CommaSplitStrategy
import global.strategy.split.SplitStrategy
import global.strategy.ui.input.ConsoleInputStrategy
import global.strategy.ui.output.ConsoleOutputStrategy

class BlackJackApplication(private val inputView: InputView, private val splitStrategy: SplitStrategy) {
    fun run() {
        val names = inputView.inputParticipantsInformation()

        /**
        val 사람 = splitStrategy.split(names)

        while(사람.상태isFinish()) {
            val command = Command.values(inputView.inputWhetherAdditionalCardAcquisition())
            사람.행동(command)
            아웃풋뷰.출력(사람)
        }


         * 사람 만들고 사람마다 y 동작
         * 사람이 가지고 있는 상태가 있으면 좋을 듯 -> Runninng, Finish
         * 상태패턴 추가
         */

    }
}

fun main() {
    val inputView = InputView(ConsoleInputStrategy, ConsoleOutputStrategy)
    BlackJackApplication(inputView, CommaSplitStrategy).run()
}
