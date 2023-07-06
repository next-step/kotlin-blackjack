package blackjack

class BlackjackGame(
    private val players: Players,
    private val cardSet: CardSet = CardSet(),
) {
    fun dealInitialHand() {
        players.forEach { player ->
            player.receive(cardSet.pop())
            player.receive(cardSet.pop())
        }
    }

    fun dealCardTo(player: Player) {
        player.receive(cardSet.pop())
    }
}
