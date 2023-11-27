package blackjack.domain

interface GameState {
    fun handOut()
    fun draw()
    fun calcSCore()
}

