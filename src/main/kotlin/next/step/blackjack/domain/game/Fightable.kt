package next.step.blackjack.domain.game

interface Fightable<T> {
    fun fight(other: T): GameResult
}