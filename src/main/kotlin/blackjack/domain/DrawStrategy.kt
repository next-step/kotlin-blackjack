package blackjack.domain

interface DrawStrategy {

    fun fetchCard(): Card

    companion object {
        const val DEAL_DRAW_COUNT = 2
    }
}
