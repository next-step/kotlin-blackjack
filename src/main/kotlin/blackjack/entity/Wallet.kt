package blackjack.entity

class Wallet (val cards : List<Card>){
  val sumUp: Int = cards.sumOf { card: Card -> card.number.value }

  fun isAbleToDraw(limit: Int): Boolean {
    return (this.sumUp < limit)
  }
}