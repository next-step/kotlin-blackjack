package blackjack.step2.domain

interface GameInteractor {
    fun askForMoreCard(participant: Participant): Boolean

    fun notifyDealerDraw()

    fun printPlayerCards(participant: Participant)
}
