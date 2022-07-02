package blackjack.entity

class Dealer(override val wallet: Wallet, override val limit: Int) : Person {
  override fun draw(): Wallet {
    TODO("Not yet implemented")
  }
}