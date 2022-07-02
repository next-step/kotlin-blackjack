package blackjack.entity

class Player(override val name: String, override val wallet: Wallet, override val limit: Int = 21) : Person {
    override fun draw(): Wallet {
        val cards = wallet.getMutableCardList()
        cards.add(CardDrawer.drawSingleCard())
        return Wallet(cards)
    }

    fun getWalletSum(): Int {
        return wallet.sumUp
    }

    fun getWalletCards(): List<Card> {
        return wallet.cards
    }
}
