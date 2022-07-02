package blackjack.entity

import blackjack.ui.GetResult

class Dealer(override val wallet: Wallet, override val name: String = "딜러", override val limit: Int = 17) : Person {
    override fun draw(wallet: Wallet): Wallet {
        val cards = wallet.getMutableCardList()
        cards.add(CardDrawer.drawSingleCard())
        return Wallet(cards)
    }

    override fun checkDrawingCondition(dealer: Person): Person {
        if (!wallet.isAbleToDraw(limit)) return dealer
        GetResult.addDealerSingleCard()
        println()
        val newWallet = dealer.draw(dealer.wallet)
        return Dealer(newWallet)
    }
}
