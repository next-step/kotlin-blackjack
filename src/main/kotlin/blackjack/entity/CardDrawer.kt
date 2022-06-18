package blackjack.entity

import blackjack.entity.Card.Companion.MAXIMUM_CARD_NUMBER
import blackjack.entity.Card.Companion.MINIMUM_CARD_NUMBER

object CardDrawer {
  fun drawSingleCard(): Card{
    return Card(getRandomShape(), getRandomNumber())
  }

  fun drawInitialCards(): List<Card>{
    return mutableListOf<Card>().apply {
      repeat(2){ this.add(drawSingleCard()) }
    }.toList()
  }

  fun drawAdditionalCard(cardList: List<Card>): List<Card> {
    val cards = cardList.toMutableList()
    cards.add(drawSingleCard())
    return cards.toList()
  }

  fun getRandomShape(): Shape {
    return Shape.values().toList().shuffled().first()
  }

  fun getRandomNumber(): Int {
    return (MINIMUM_CARD_NUMBER..MAXIMUM_CARD_NUMBER).random()
  }
}