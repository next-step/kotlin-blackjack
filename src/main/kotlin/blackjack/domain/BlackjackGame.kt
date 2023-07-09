package blackjack.domain

class BlackjackGame(
    val challengers: Challengers,
    val dealer: Dealer = Dealer(),
    private val cardSet: CardSet = CardSet(),
) {
    fun dealInitialHand() {
        challengers.forEach { player ->
            player.receive(cardSet.pop())
            player.receive(cardSet.pop())
        }
        repeat(2) {
            dealer.receive(cardSet.pop())
        }
    }

    fun dealCardTo(player: Player) {
        player.receive(cardSet.pop())
    }
}
