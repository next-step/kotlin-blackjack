package blackjack

fun main() {
    val playerNames = InputView.inputPlayerNames()
    BlackJack.start(playerNames, Deck())
}