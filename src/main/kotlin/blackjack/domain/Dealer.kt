package blackjack.domain

class Dealer(
    override val name: String = "딜러",
    override val hands: Hands = Hands(),
    override var status: GameStatus = GameStatus.PLAYING,
) : Participant(name, hands, status) {
    infix fun vs(player: Player): Result {
        return when {
            status == GameStatus.BURST -> Result.LOSE
            player.status == GameStatus.BURST -> Result.WIN
            score > player.score -> Result.WIN
            score < player.score -> Result.LOSE
            else -> Result.DRAW
        }
    }

    override fun initialDraw(deck: Deck) {
        super.initialDraw(deck)

        if (!GameStatus.isOverDealerThreshold(score)) {
            hit(deck)
        }

        handleStatus()
    }

    override fun handleStatus() {
        status =
            when {
                GameStatus.isBurst(score) -> GameStatus.BURST
                GameStatus.isOverDealerThreshold(score) -> GameStatus.STAY
                else -> GameStatus.PLAYING
            }
    }
}
