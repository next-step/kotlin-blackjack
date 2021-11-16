package blackjack

import blackjack.domain.Deck
import blackjack.domain.Players
import blackjack.strategy.assign.FirstAssignCardStrategy
import blackjack.strategy.shuffle.DeckRandomShuffleStrategy
import blackjack.strategy.split.CommaSplitStrategy
import blackjack.strategy.split.SplitStrategy
import blackjack.strategy.ui.input.ConsoleInputStrategy
import blackjack.strategy.ui.output.ConsoleOutputStrategy
import blackjack.ui.InputView

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
class BlackJackApplication(private val inputView: InputView, private val splitStrategy: SplitStrategy) {
    fun run() {
        val deck = Deck.initialize(DeckRandomShuffleStrategy)
        var players = players().assignCards(deck, FirstAssignCardStrategy)
    }

    private fun players(): Players {
        return try {
            Players.of(inputView.inputParticipantsInformation(), CommaSplitStrategy)
        } catch (e: Exception) {
            println(e.message)
            players()
        }
    }
}

fun main() {
    val inputView = InputView(ConsoleInputStrategy, ConsoleOutputStrategy)
    BlackJackApplication(inputView, CommaSplitStrategy).run()
}
