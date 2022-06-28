package blackjack

interface RequestView {
    fun moreCardToDealer()
    fun moreCardToGamer(name: String)
}
