package blackjack.view

import blackjack.model.Participants
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
}

private fun Participants.present(): String {
    return this.participants.joinToString(separator = "\n") { it.present() }
}

private fun Participants.presentWithScore(): String {
    return this.participants.joinToString(separator = "\n") { "${it.present()} - 결과: ${it.cards.totalScore()}" }
}
