import domain.CardDeck
import domain.Dealer
import presentation.InputView

fun main() {
    val dealer = Dealer()
    val players = InputView.inputPlayers()
    println(players)

    val deck = CardDeck()
}
