package blackjack

fun main() {

    PrintView.printInputUserNamesDesc()
    val userNames = InputView.getUserNames()

    val players = userNames.map { Player(it) }
}
