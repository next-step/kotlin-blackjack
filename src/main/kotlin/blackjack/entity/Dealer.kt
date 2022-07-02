package blackjack.entity

class Dealer(override val name: String, override val wallet: Wallet, override val limit: Int) : Person {
  override fun draw(): Wallet {
    TODO("Not yet implemented")
  }

  override fun checkDrawingCondition(): Person {
    return Dealer(name, wallet, limit)
  }
}