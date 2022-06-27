package blackjack.entity

object Deck {
  val cards = Shape.values()
    .flatMap {shape ->
      CardNumber
        .values()
        .map { number -> Card(shape, number) }
    }
    .shuffled()
    .toMutableList()
}