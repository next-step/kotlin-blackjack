package blackjack.entity

class Player(override val name: String, override val wallet: Wallet, override val limit: Int = 21) : Person {
    override fun draw(): Wallet {
        val cards = wallet.getMutableCardList()
        cards.add(CardDrawer.drawSingleCard())
        return Wallet(cards)
    }

    override fun checkDrawingCondition(): Person {
        return Player(name, wallet, limit)
    }
}
