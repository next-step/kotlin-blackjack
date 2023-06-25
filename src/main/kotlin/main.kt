import blackjack.domain.game.BlackJackGameFactory
import blackjack.domain.shuffle.CardShuffler
import blackjack.view.BlackJackController
import blackjack.view.BlackJackInputView
import blackjack.view.BlackJackResultView

fun main() {
    BlackJackController(
        inputView = BlackJackInputView(),
        resultView = BlackJackResultView(),
        blackJackGameFactory = BlackJackGameFactory(CardShuffler()),
    ).start()
}
