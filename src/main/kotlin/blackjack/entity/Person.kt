package blackjack.entity

interface Person {
  val name: String
  val wallet: Wallet
  val limit: Int

  fun draw(): Wallet

  fun getWalletCards(): List<Card> {
    return wallet.cards
  }

  fun getWalletSum(): Int {
    return wallet.sumUp
  }

  fun checkDrawingCondition(player: Person): Person
}
