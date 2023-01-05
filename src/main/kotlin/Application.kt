import controller.BlackjackGame
import model.CardNumberCalculator
import model.CardVendor
import model.Players

fun main() {
    BlackjackGame(Players(), CardNumberCalculator(), CardVendor()).start()
}
