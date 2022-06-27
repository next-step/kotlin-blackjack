package blackjack.entity

// todo: limit 이 static 한 변수일 때, inferface에서 선언하는 방법
class Player(override val name: String, override val wallet: Wallet, override val limit: Int = 21) : Person {
    override fun draw(): Wallet {
        val cards = wallet.getMutableCardList()
        cards.add(CardDrawer.drawSingleCard())
        return Wallet(cards)
    }
}
