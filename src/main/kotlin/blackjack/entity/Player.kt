package blackjack.entity

import blackjack.ui.GetResult
import blackjack.ui.Input

class Player(override val name: String, override val wallet: Wallet, override val limit: Int = 21) : Person {
    override fun draw(wallet: Wallet): Wallet {
        return wallet.addNewCard()
    }
}
