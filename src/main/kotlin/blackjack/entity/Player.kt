package blackjack.entity

import blackjack.ui.GetResult
import blackjack.ui.Input

class Player(override val name: String, override val wallet: Wallet, override val limit: Int = 21) : Person {
    override fun draw(wallet: Wallet): Wallet {
        val cards = wallet.getMutableCardList()
        cards.add(CardDrawer.drawSingleCard())
        return Wallet(cards)
    }

    override fun chooseDrawing(wallet: Wallet): Wallet {
        if (!wallet.isAbleToDraw(limit)) return wallet
        if (Input.additionalCard(name) == "n") return wallet
        val newPlayer = Player(name, draw(wallet))
        GetResult.printPlayerStatus(newPlayer)
        return chooseDrawing(wallet)
    }
}
