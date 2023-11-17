package blackjack.view

import blackjack.model.Participants
import blackjack.model.Player
import blackjack.view.Console.present

object OutputView {

    fun presentCards(participants: Participants) {
        println(participants.present())
    }

    fun result(participants: Participants) {
        presentCardsWitScore(participants)
    }

    private fun presentCardsWitScore(participants: Participants) {
        println()
        println(participants.presentWithScore())
    }

    fun dealing(participants: Participants) {
        println("${participants.names()} 에게 2 장씩 나누었습니다.")
    }

    fun playerCardPresent(it: Player) {
        println(it.present())
    }
}

private fun Participants.present(): String {
    return this.participants.joinToString(separator = "\n") { it.present() }
}

private fun Participants.presentWithScore(): String {
    return this.participants.joinToString(separator = "\n") { "${it.present()} - 결과: ${it.cards.totalScore()}" }
}

private fun Participants.names(): String {
    return participants.joinToString(separator = InputView.PARTICIPANTS_PRESENT_SEPARATOR) { it.name }
}
