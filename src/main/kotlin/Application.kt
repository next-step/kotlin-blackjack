import controller.BlackjackGame
import controller.CardVendor
import model.Players
import view.InputView
import view.ResultView

fun main() {
    BlackjackGame(Players(), CardVendor(), InputView(), ResultView()).start()
}
