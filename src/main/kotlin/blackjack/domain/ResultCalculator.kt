package blackjack.domain

fun Player.getResult(dealer: Dealer): GameResult {
    if (dealer.isBust()) {
        return GameResult.WIN
    }
    if (isBust()) {
        return GameResult.LOSE
    }

    if (state.getSum() > dealer.state.getSum()) {
        return GameResult.WIN
    }
    if (state.getSum() < dealer.state.getSum()) {
        return GameResult.LOSE
    }
    return GameResult.DRAW
}

fun Dealer.getResult(player: Player): GameResult {
    if (isBust()) {
        return GameResult.LOSE
    }
    if (player.isBust()) {
        return GameResult.WIN
    }

    if (state.getSum() > player.state.getSum()) {
        return GameResult.WIN
    }
    if (state.getSum() < player.state.getSum()) {
        return GameResult.LOSE
    }
    return GameResult.DRAW
}
