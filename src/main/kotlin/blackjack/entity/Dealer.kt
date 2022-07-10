package blackjack.entity

import blackjack.ui.GetResult

class Dealer(override val wallet: Wallet, override val name: String = "딜러", override val limit: Int = 17) : Person {
    override fun draw(wallet: Wallet): Wallet {
        return wallet.addNewCard()
    }
}
