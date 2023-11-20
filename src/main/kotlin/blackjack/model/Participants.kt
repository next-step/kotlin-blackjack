package blackjack.model

class Participants(
    val players: Players,
    val dealer: Dealer
) {

    fun initialCardDealing() {
        players.initialCardDealing(dealer)
        dealer.initialCardDealing()
    }

    fun makeResult() {
        dealer.compareWithPlayers(players)
        getPrice()
    }

    fun processGame(
        moreCardComment: (Boolean) -> Unit,
        hitOrStand: (Player) -> Boolean,
        showCard: (Player) -> Unit
    ) {
        players.values.forEach {
            it.processGame(dealer, hitOrStand, showCard)
        }
        moreCardComment(dealer.moreCard())
    }

    private fun getPrice() {
        var dealerMoney = dealer.bettingMoney
        players.values.forEach {
            dealerMoney -= it.getPrice()
        }
        dealer.resultMoney = dealerMoney
    }
}
