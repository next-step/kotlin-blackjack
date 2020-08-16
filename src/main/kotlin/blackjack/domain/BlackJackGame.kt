package blackjack.domain

class BlackJackGame(
    private val deck: Deck
) {
    fun getDealer() = Dealer().deal(deck)

    fun dealWith(players: List<String>, getBettingMoney: (name: String) -> Int): List<Player> {
        return players.asSequence()
            .map { PlayerInfo(it, getBettingMoney(it)) }
            .map { Challenger(it) }
            .map { it.deal(deck) }
            .toList()
    }

    tailrec fun playDealer(dealer: Player, hitNotice: () -> Unit = {}): Player {
        if (dealer.canPlay()) {
            hitNotice()
            return playDealer(dealer.hit(deck), hitNotice)
        }
        return dealer
    }

    tailrec fun play(
        players: List<Player>,
        acc: List<Player> = emptyList(),
        isAgreedHit: (Player) -> Boolean,
        printPlayerCards: (Player) -> Unit
    ): List<Player> {
        if (players.isEmpty()) return acc
        val player = players.first().also(printPlayerCards)
        if (player.canPlay() && isAgreedHit(player)) {
            return play(player.hit(deck) + players.drop(1), acc, isAgreedHit, printPlayerCards)
        }
        return play(players.drop(1), acc + player, isAgreedHit, printPlayerCards)
    }
}
