package blackjack

import blackjack.view.InputView

fun main() {
    val blackJackApplication = BlackJackApplication()
    blackJackApplication.run()
}

class BlackJackApplication {
    fun run() {
        InputView.inputPlayerNames()
    }
}
