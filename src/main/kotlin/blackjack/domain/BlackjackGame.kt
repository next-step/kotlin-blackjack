package blackjack.domain

class BlackjackGame(
    val challengers: Challengers,
    val dealer: Dealer = Dealer(),
    private val cardSet: CardSet = CardSet(),
) {
    fun dealInitialHand() {
        repeat(2) {
            challengers.forEach { player ->
                player.receive(cardSet.pop())
            }
            dealer.receive(cardSet.pop())
        }
    }

    fun dealCardTo(player: Player) {
        player.receive(cardSet.pop())
    }
}
