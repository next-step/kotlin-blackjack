package blackjack

fun main() {
    val cardDeck = CardDeck()
    val players = InputView.shareCard(cardDeck)

    for (player in players) {
        InputView.hitCard(player, cardDeck)
    }

    InputView.showResult(players)
}
