package blackjack.entity

class Dealer(override val name: String = "딜러", override val wallet: Wallet, override val limit: Int = 17) : Person {
  override fun draw(): Wallet {
    TODO("Not yet implemented")
  }

  override fun checkDrawingCondition(): Person {
    return Dealer(name, wallet, limit)
  }
}