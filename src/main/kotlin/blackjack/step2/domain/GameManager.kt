package blackjack.step2.domain

class GameManager(
    private val cardPicker: CardPicker,
    private val interactor: GameInteractor,
) {
    fun playTurn(participant: Participant): Participant {
        return participant.playTurn(cardPicker, interactor)
    }
}
