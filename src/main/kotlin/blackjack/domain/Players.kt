package blackjack.domain

class Players(val list: List<Player>) {
    fun drawAllPlayer(cardDeck: CardDeck) {
        list.forEach { it.draw(cardDeck) }
    }

    fun getBetResults(dealer: Dealer): List<BetResult> {
        return list.map { player ->
            BetResult(player, player.betMoney, dealer.getBetResult(player))
        }
    }

    fun forEach(action: (Player) -> Unit) = list.forEach(action)
}
