import blackjack.view.BlackJackController
import blackjack.view.BlackJackInputView

fun main() {
    BlackJackController(
        inputView = BlackJackInputView(),
    ).start()
}
