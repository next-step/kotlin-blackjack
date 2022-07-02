package blackjack.entity

import blackjack.ui.GetResult
import blackjack.ui.Input

class Player(override val name: String, override val wallet: Wallet, override val limit: Int = 21) : Person {
    override fun draw(): Wallet {
        val cards = wallet.getMutableCardList()
        cards.add(CardDrawer.drawSingleCard())
        return Wallet(cards)
    }

    override fun checkDrawingCondition(player: Person): Person {
        if (!player.wallet.isAbleToDraw(player.limit)) return player
        if (Input.additionalCard(name) == "n") return player
        val newPlayer = Player(name, draw())
        GetResult.printPlayerStatus(newPlayer)
        return checkDrawingCondition(newPlayer)
    }
}
