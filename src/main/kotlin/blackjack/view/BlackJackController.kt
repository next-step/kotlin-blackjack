package blackjack.view

class BlackJackController(
    private val inputView: BlackJackInputView,
) {

    fun start() {
        val playerNames = inputView.readPlayerNames()
    }
}
