package blackjack.entity

interface Person {
    val name: String
    val hands: Hands
    val limit: Int

    fun draw(hands: Hands): Hands

    fun getWalletCards(): List<Card> {
        return hands.cards
    }

    fun getWalletSum(): Int {
        return hands.sumUp
    }
}
