package blackjack.domain

interface DrawStrategy {

    fun fetchCard(): Card

    fun getDealCards(): List<Card>

    companion object {
        const val DEAL_DRAW_COUNT = 2
    }
}
