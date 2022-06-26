package blackjack

abstract class UserRole(
    open val name: String,
    open val gameStatus: GameStatus = GameStatus()
) {
    val cards: List<Card>
        get() = gameStatus.state.currentCard()
            .cards
            .toList()

    abstract fun draw(card: Card): UserRole

    abstract fun stand(): UserRole

    abstract fun isFinish(): Boolean

    abstract fun isDealer(): Boolean

    fun getScore(): Int = gameStatus.state.score(gameStatus.state.currentCard().cards)
}
