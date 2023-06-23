package blackjack

class Round(private val trump: Trump = Trump()) {
    fun getCard(): Card = trump.getCard()

    companion object {
        const val TWENTY_ONE = 21
    }
}
