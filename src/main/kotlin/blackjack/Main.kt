package blackjack

fun main() {
    val cardDeck = CardDeck()
    val players = InputView.enterPlayers()

    println("${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
    for (player in players) {
        player.getInitialCards(cardDeck.drawIntialCards())
        ResultView.showPlayerCards(player)
    }

    for (player in players) {
        player.callHit(cardDeck)
    }

    ResultView.showResult(players)
}

private fun Player.callHit(cardDeck: CardDeck) {
    var isHitCalled = InputView.isHitCalled(this)
    while (isHitCalled) {
        hit(cardDeck.drawCard())
        ResultView.showPlayerCards(this)
        if (isBusted) break

        isHitCalled = InputView.isHitCalled(this)
    }
}
