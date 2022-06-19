package blackjack.entity

interface Person {
  val name: String
  val wallet:Wallet
  val limit: Int
  fun draw(answer: String): Wallet

}