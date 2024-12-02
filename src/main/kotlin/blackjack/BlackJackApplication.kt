package blackjack

fun main() {
    val playerNames = InputView.inputPlayerNames()
    val players = BlackJack.start(playerNames, Deck())
    OutputView.printPlayersStartCardPack(players)
}