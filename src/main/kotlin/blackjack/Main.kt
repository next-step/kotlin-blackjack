package blackjack

fun main() {
    val cardDeck = CardDeck()
    val players = InputView.shareCard(cardDeck)
    for (player in players) ResultView.showPlayerCards(player)

    for (player in players) {
        var isHitCalled = InputView.isHitCalled(player)
        while (isHitCalled) {
            player.hit(cardDeck.drawCard())
            ResultView.showPlayerCards(player)
            if (player.isBusted) break

            isHitCalled = InputView.isHitCalled(player)
        }
    }

    ResultView.showResult(players)
}
