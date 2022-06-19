package blackjack.entity

object CardDrawer {
  fun drawSingleCard(): Card{
    return Card(getRandomShape(), getRandomNumber())
  }

  fun drawInitialCards(): Wallet{
    return Wallet(mutableListOf<Card>().apply {
      repeat(2){ this.add(drawSingleCard()) }
    }.toList())
  }

  fun drawAdditionalCard(cardList: List<Card>): Wallet {
    val cards = cardList.toMutableList()
    cards.add(drawSingleCard())
    return Wallet(cards)
  }

  fun getRandomShape(): Shape {
    return Shape.values().toList().shuffled().first()
  }

  fun getRandomNumber(): CardNumber {
    return CardNumber.values().toList().shuffled().first()
  }
}