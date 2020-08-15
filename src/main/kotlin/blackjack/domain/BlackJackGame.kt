package blackjack.domain

class BlackJackGame(
    private val deck: Deck
) {
    fun getDealer() = Dealer().deal(deck)

    fun dealWith(players: List<String>): List<Player> {
        return players.map { Challenger(it) }
            .map { it.deal(deck) }
    }

    tailrec fun playDealer(dealer: Player, hitNotice: () -> Unit = {}): Player {
        if (dealer.state == State.Playing) {
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
        val player = askHit(players.first()) { isAgreedHit(it) }
        printPlayerCards(player)
        if (player.state == State.Playing) {
            return play(player + players.drop(1), acc, isAgreedHit, printPlayerCards)
        }
        return play(players.drop(1), acc + player, isAgreedHit, printPlayerCards)
    }

    private fun askHit(player: Player, isAgreedHit: (Player) -> Boolean): Player {
        if (isAgreedHit(player)) return player.hit(deck)
        return player.stand()
    }
}
