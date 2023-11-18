package blackjack.domain

infix fun Player.calculateResult(dealer: Dealer): GameResult {
    if (dealer.isBust()) {
        return GameResult.WIN
    }
    if (isBust()) {
        return GameResult.LOSE
    }

    if (hand.getSum() > dealer.hand.getSum()) {
        return GameResult.WIN
    }
    if (hand.getSum() < dealer.hand.getSum()) {
        return GameResult.LOSE
    }
    return GameResult.DRAW
}
