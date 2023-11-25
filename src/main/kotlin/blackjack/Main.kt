package blackjack

fun main() {
    val cardDeck = CardDeck()
    val players = InputView.enterPlayers()
    val dealer = Dealer()

    ResultView.showCardShare(players)
    dealer.setInitialDeck(cardDeck, ResultView::showInitialCards)
    for (player in players) {
        player.setInitialDeck(cardDeck, ResultView::showInitialCards)
    }

    for (player in players) {
        player.callHit(cardDeck)
    }
    dealer.getCard(cardDeck)

    ResultView.showCardScore(dealer)
    ResultView.showCardScore(*players.toTypedArray())

    val result = GameResultDeterminer.getResult(players, dealer)
    ResultView.showResult(result)
}

private fun Dealer.getCard(cardDeck: CardDeck) {
    var count = 0
    while (canGetCard) {
        count++
        hit(cardDeck.drawCard())
    }

    ResultView.showDealerDrawCount(count)
}

private fun Player.callHit(cardDeck: CardDeck) {
    var isHitCalled = InputView.isHitCalled(this)
    while (isHitCalled) {
        hit(cardDeck.drawCard())
        ResultView.showPlayerCards(this)
        if (!canGetCard) break

        isHitCalled = InputView.isHitCalled(this)
    }
}
